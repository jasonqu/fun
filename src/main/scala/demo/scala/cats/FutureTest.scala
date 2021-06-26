package demo.scala.cats

import cats.Semigroupal
import cats.instances.future._ // for Semigroupal
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.higherKinds

object FutureTest extends App {
  def returnAfterSleepInMillis[T](idx: Int, sleep:Int, value: T) = Future {
    println(s"future $idx in " + System.currentTimeMillis())
    Thread.sleep(sleep)
    value
  }

//  val f: Future[String] = returnAfterSleepInMillis(1, 500, "Hello")
//  Await.result(f, 700.millis)
//  println(f.value + " " + System.currentTimeMillis())

//  val futureSeq = for {
//    f1 <- returnAfterSleepInMillis(1, 500, "Hello")
//    f2 <- returnAfterSleepInMillis(2, 800, "123")
//  } yield (f1, f2)
//  futureSeq.foreach(_ => println(System.currentTimeMillis()))
//  Await.result(futureSeq, 2.second)

  val futurePair = Semigroupal[Future].product(
    returnAfterSleepInMillis(3, 500, "World"),
    returnAfterSleepInMillis(4, 800, "456"))
  futurePair.foreach(_ => println(System.currentTimeMillis()))
  Await.result(futurePair, 1.second)
}
