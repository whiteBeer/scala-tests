package com.sj.scala

import akka.actor.ActorSystem
import scala.util.{Failure, Success}
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher, Route}

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import com.sj.scala.DataBase

object HealthRoute {

  val db = new DataBase

  val organizationsRoute: Route =
    path("organizations") {
      onComplete(db.findOrganizarions()) {
        case Success(dbData) => {
          complete("DB Organizations: " + dbData.map(x => x.name).mkString(", "))
        }
        case Failure(data) => {
          complete("Fail " + data)
        }
      }
    }
}

object SimpleHttp extends App {

  implicit val system: ActorSystem = ActorSystem("simple-http")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  implicit val log = Logging(system, "main")

  val port = 8080

  val bindingFuture =
    Http().bindAndHandle(HealthRoute.organizationsRoute, "localhost", port)

  log.info(s"Server started at the port $port")
}
