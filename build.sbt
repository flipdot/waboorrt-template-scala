ThisBuild / scalaVersion := "2.13.7"
ThisBuild / organization := "org.flipdot"

lazy val hello = (project in file("."))
  .settings(
    name := "WaboorrtBot",
    //libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.github.arteam" % "simple-json-rpc-server" % "1.2",
  )
