package demo.scala.cats

import cats.Semigroupal
import cats.instances.future._ // for Semigroupal
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.higherKinds

object FutureTest extends App {
  val futureSeq = for {
    f1 <- Future {
      println("future1 in " + System.currentTimeMillis())
      Thread.sleep(500)
      "Hello"
    }
    f2 <- Future {
      println("future2 in " + System.currentTimeMillis())
      Thread.sleep(800)
      "123"
    }
  } yield (f1, f2)
  futureSeq.foreach(println)
  Await.result(futureSeq, 2.second)

  val futurePair = Semigroupal[Future].product(Future {
    println("future3 in " + System.currentTimeMillis())
    Thread.sleep(500)
    "Hello"
  }, Future {
    println("future4 in " + System.currentTimeMillis())
    Thread.sleep(800)
    "123"
  })
  futurePair.foreach(println)
  Await.result(futurePair, 1.second)
}
