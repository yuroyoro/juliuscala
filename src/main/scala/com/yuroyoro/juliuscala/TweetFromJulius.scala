package com.yuroyoro.juliuscala

object TweetFromJulius extends App {
  import com.yuroyoro.juliuscala._

  import dispatch._
  import Http._
  import json.Js._
  import twitter._
  import oauth._

  val server = {
    val props = properties("julius.properties")
    val path   = props.get("julius_path").get
    val config = props.get("julius_conf").get

    Julius.start(path, config)
  }

  Thread.sleep(3000)

  server.connect{ node =>
    node \\ "WHYPO" \\ "@WORD" mkString match {
      case word if word.length > 2 => tweet(word)
      case _ =>
    }
  }

  def tweet(word:String) = {
    val req = Status.update(word, consumer, token)
    http(req >>> System.out)
  }

  lazy val http = new Http
  lazy val consumer = {
    val props = properties("consumer.properties")
    Consumer(props.get("key").get, props.get("secret").get)
  }

  lazy val token = {
    val props = properties("accesstoken.properties")
    Token(props).get
  }

  def properties(name:String) = {
    import scala.collection.JavaConversions._

    val props = new java.util.Properties
    props.load(new java.io.FileInputStream(name))
    props.collect{case (k,v) => (k.toString, v.toString)}
  }

}

