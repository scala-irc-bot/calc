// -*- scala -*-

name := "calc"

organization := "net.mtgto"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers += "mtgto repos" at "http://scala-irc-bot.github.com/scala-irc-bot/maven/"

libraryDependencies := Seq(
  "net.mtgto" %% "scala-irc-bot" % "0.1.0-SNAPSHOT",
  "junit" % "junit" % "4.10" % "test",
  "org.specs2" %% "specs2" % "1.12.2" % "test"
)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-encoding", "UTF8")

testOptions in Test += Tests.Argument("junitxml")

seq(ScctPlugin.instrumentSettings : _*)

org.scalastyle.sbt.ScalastylePlugin.Settings
