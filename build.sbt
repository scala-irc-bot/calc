// -*- scala -*-

name := "calc"

organization := "net.mtgto"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.2", "2.10.0")

licenses := Seq("BSD license" -> url("https://github.com/scala-irc-bot/calc/blob/master/LICENSE.txt"))

resolvers += "mtgto repos" at "http://scala-irc-bot.github.com/scala-irc-bot/maven/"

libraryDependencies := Seq(
  "net.mtgto" %% "scala-irc-bot" % "0.2.0-SNAPSHOT",
  "junit" % "junit" % "4.10" % "test",
  "org.specs2" %% "specs2" % "1.12.3" % "test"
)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-encoding", "UTF8")

ScctPlugin.instrumentSettings

testOptions in ScctTest += Tests.Argument(TestFrameworks.Specs2, "console", "junitxml")

org.scalastyle.sbt.ScalastylePlugin.Settings
