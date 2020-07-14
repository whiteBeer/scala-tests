import sbt._

object Dependencies {
  lazy val akkaVersion = "2.5.23"
  lazy val akkaHttpVersion = "10.1.8"

  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion

  lazy val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
  lazy val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  lazy val specs2Core = "org.specs2" %% "specs2-core" % "4.5.1"

  lazy val reactiveMongo = "org.reactivemongo" %% "reactivemongo" % "0.13.0"
  lazy val reactiveMongo1 = "org.reactivemongo" %% "reactivemongo-iteratees" % "0.13.0"
  lazy val reactiveMongo2 = "org.reactivemongo" %% "reactivemongo-akkastream" % "0.13.0"
  lazy val slf4j = "org.slf4j" % "slf4j-api" % "1.7.25"
  lazy val slf4j2 = "org.slf4j" % "slf4j-simple" % "1.7.25"

}
