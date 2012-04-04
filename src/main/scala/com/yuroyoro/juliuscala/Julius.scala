package com.yuroyoro.juliuscala

object Julius {

  class Server(path:String, config:String){
    import scala.sys.process._
    import scala.xml.{XML, Node}
    import java.net._
    import java.io._

    def command = "%s/julius -C %s -charconv EUC-JP UTF-8 -module" format(path, config)
    val process = Process(command).run

    def stop = process.destroy

    def connect(f:Node => Unit) = {
      val socket = new Socket("localhost", 10500)
      val in = socket.getInputStream()

      val buf = scala.collection.mutable.ArrayBuffer.empty[String]
      consume(in) {
        case "." =>
          val s = buf.mkString("\n").replaceAll("\\<\\/?s\\>", "&lt;\\/s&gt;")
          println("-" * 80)
          println(s)
          println("-" * 80)
          val xml = XML.loadString(s)
          buf.clear
          f(xml)
        case l   =>  buf += l
      }
    }

    private def consume(in:InputStream)( f:String => Unit){
      val buf = new Array[Byte](1024)
      var remains:String = ""
      try{
        // InputStreamから1行読んでfにわたす
        for(i <- Stream.continually(in.read(buf)).takeWhile(_ != -1)){
          val str = remains + new String(buf,  0,  i)
          remains = ( "" /: str){ (s,c) =>
            if( c == '\n'){
              f(s)
              ""
            }
            else s + c
          }
        }
     }
     catch{ case e:IOException =>
       e.printStackTrace
     }
     finally{ in.close }
    }

  }

  def start(path:String, config:String) = new Server(path, config)

}

