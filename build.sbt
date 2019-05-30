import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "oen.cats.trampoline.showcase"
ThisBuild / organizationName := "oen"

lazy val root = (project in file("."))
  .settings(
    name := "cats-trampoline-showcase",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "1.3.1",
      scalaTest % Test
    )
  ).enablePlugins(JavaAppPackaging)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
