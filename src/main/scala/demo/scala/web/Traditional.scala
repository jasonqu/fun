package demo.scala.web

object Traditional {
  type HttpRequest = String
  type HttpResponse = String

  type WebRequest = HttpRequest => HttpResponse

  trait Serialiser {
    def serialise(c: Customer): HttpResponse
    def deserialise(input: HttpRequest): Person
  }

  class WebController(service: ApplicationService,
                       serialiser: Serialiser) {
    def register(p: HttpRequest): HttpResponse = {
      val person = serialiser.deserialise(p)
      val c = service.registerCustomer(person)
      serialiser.serialise(c)
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


