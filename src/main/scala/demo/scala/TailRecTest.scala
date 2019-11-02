package demo.scala
import scala.annotation.tailrec

object TailRecTest extends App {
  def fact(n : Int): Int =
    if(n == 0) 1 else n * fact(n - 1)

  def fact2(n : Int): Int = {
    @tailrec
    def innerFact(accu: Int, c: Int) : Int =
      if(c == 0) accu else innerFact(accu * c, c - 1)
    innerFact(1, n)
  }


  println(fact(6))
  println(fact2(6))
}
