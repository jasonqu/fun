package demo.scala.cats

import scala.io.StdIn

object Statements extends App {
  println("Enter an Int that you'd like to divide:")
  val dividend = StdIn.readLine().toInt
  println("Enter an Int that you'd like to divide by:")
  val divisor = StdIn.readLine().toInt
  val result = dividend / divisor
  println(s"result is $result")
}
