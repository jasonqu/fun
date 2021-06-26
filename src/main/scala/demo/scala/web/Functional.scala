package demo.scala.web

import Model._

object Functional {

  object Serialiser {
    def serialise(c: Customer): HttpResponse = ???

    def deserialise(input: HttpRequest): Person = ???
  }

  val deSerialisePerson: HttpRequest => Person =
    Serialiser.deserialise
  val createCustomer: Person => Customer =
    p => Customer(1, p)
  val saveCustomer: Customer => Customer = ???
  val serialiseCustomer: Customer => HttpResponse =
    Serialiser.serialise

  val registerCustomer: WebRequest =
    deSerialisePerson andThen createCustomer andThen
      saveCustomer andThen serialiseCustomer

  type DatabaseConnection = String

  val dbConnection = new DatabaseConnection

  object DataAccess {
    def saveCustomer(db: DatabaseConnection)
                    (c: Customer): Customer = ???
  }

  val saveCustomerImpl: Customer => Customer =
    DataAccess.saveCustomer(dbConnection)
}
