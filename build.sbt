import Dependencies._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "Akka Http Skeleton",
    libraryDependencies ++= Seq(
      reactiveMongo,
      reactiveMongo1,
      reactiveMongo2,
      slf4j,
      slf4j2,
      akkaHttp,
      akkaStream,
      akkaTestKit % Test,
      akkaHttpTestKit % Test,
      specs2Core % Test
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
