import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "juiluscala",
      organization := "com.yuroyoro",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.1"
      // add other settings here
    )
  ) dependsOn(dispatchTwitter)
  lazy val dispatchTwitter = uri("git://github.com/n8han/dispatch-twitter#0.1.4")
}
