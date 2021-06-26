package demo.scala.web

case class Person(name: String)

case class Customer(customerNumber: Int, p: Person)

object Model {
  type HttpRequest = String
  type HttpResponse = String

  type WebRequest = HttpRequest => HttpResponse
}