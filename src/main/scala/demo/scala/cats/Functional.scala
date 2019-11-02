package demo.scala.cats


import demo.scala.web.{Customer, Person}
import demo.scala.web.Traditional.{HttpRequest, HttpResponse}

import scala.concurrent.Future
import scala.util.Try
//import cats._
//import cats.implicits._
import scala.concurrent.ExecutionContext.Implicits.global

object Functional {
  type DatabaseConnection = String

  type WebRequest = HttpRequest => Future[HttpResponse]

  object Serialiser {
    def serialise(c: Customer): HttpResponse = ???
    def deserialise(input: HttpRequest): Try[Person] = ???
  }

  val deSerialisePerson: HttpRequest => Try[Person] =
    Serialiser.deserialise
  val createCustomer: Person => Future[Customer] =
    (p: Person) => Future.successful(Customer(1, p))
  val saveCustomer: Customer => Future[Customer] =
    DataAccess.saveCustomer(dbConnection)
  val serialiseCustomer: Customer => HttpResponse =
    Serialiser.serialise

  val dbConnection = new DatabaseConnection
  object DataAccess {
    def saveCustomer(db: DatabaseConnection)
                    (c: Customer): Future[Customer] = ???
  }

  val registerCustomer: WebRequest = req =>
    for {
      person <- Future.fromTry(deSerialisePerson(req))
      c1 <- createCustomer(person)
      c2 <- saveCustomer(c1)
    } yield serialiseCustomer(c2)

}
