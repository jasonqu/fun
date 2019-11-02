package demo.scala

object ImmutableTest extends App {
  val i = 10
  //i = 11

  val list1 = List(1, 2, 3)
  val list2 = 0 :: list1.tail

  case class Person(name: String, age: Int)
}
