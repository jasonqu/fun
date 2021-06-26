package demo.scala.web

import Model._

object Traditional {

  trait Serialiser {
    def serialise(c: Customer): HttpResponse

    def deserialise(input: HttpRequest): Person
  }

  class WebController(service: ApplicationService,
                      serialiser: Serialiser) {
    def register(req: HttpRequest): HttpResponse = {
      val person: Person = serialiser.deserialise(req)
      val customer: Customer = service.registerCustomer(person)
      serialiser.serialise(customer)
    }
  }

  class ApplicationService(dataStore: CustomerRepo) {
    def registerCustomer(p: Person): Customer = {
      val c = Customer(1, p)
      dataStore.saveCustomer(c)
      c
    }
  }

  trait CustomerRepo {
    def saveCustomer(p: Customer): Unit
  }

}


