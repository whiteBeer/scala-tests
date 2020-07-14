package com.sj.scala.db

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.Try
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{Cursor, DefaultDB, MongoConnection, MongoDriver}
import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros, document}

case class Organization(name: String)

class DataBase {

  implicit def organizationReader: BSONDocumentReader[Organization] = Macros.reader[Organization]

  val driver: MongoDriver = new MongoDriver
  val mongoUri = "mongodb://localhost:27017/clientbook"
  val parsedUri: Try[MongoConnection.ParsedURI] = MongoConnection.parseURI(mongoUri)
  val connection: Try[MongoConnection] = parsedUri.map(driver.connection(_))
  val futureConnection: Future[MongoConnection] = Future.fromTry(connection)

  val db: Future[DefaultDB] = futureConnection.flatMap(_.database("clientbook"))
  def collection: Future[BSONCollection] = db.map(db => db.collection("organizations"))

  def findOrganizarions():Future[List[Organization]] =
    collection.flatMap(
      _.find(document()).
        cursor[Organization]().
        collect[List](-1, Cursor.FailOnError[List[Organization]]())
    )
}
