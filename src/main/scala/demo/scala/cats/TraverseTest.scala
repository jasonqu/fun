package demo.scala.cats

import scala.concurrent.Future
import cats._
import cats.implicits._
import scala.concurrent.ExecutionContext.Implicits.global

object TraverseTest extends App {

  def singleQuery(i : Int): Future[String] = Future {
    println("start in " + System.currentTimeMillis())
    Thread.sleep(100)
    "res " + i
  }

  val res = Traverse[List].traverse(1 to 3 toList)(singleQuery)
  res.foreach(println)

  Thread.sleep(1000)
}
