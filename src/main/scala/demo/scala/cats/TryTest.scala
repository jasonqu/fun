package demo.scala.cats
import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.{Try, Success, Failure}

object TryTest extends App {
  println(divideWithRetry)

  def divide: Try[Int] = {
    val dividend = Try(StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt)
    val divisor = Try(StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt)
    val problem = dividend.flatMap(x => divisor.map(y => x/y))
    problem match {
      case Success(v) =>
        println("Result of " + dividend.get + "/"+ divisor.get +" is: " + v)
        Success(v)
      case Failure(e) =>
        println("You must've divided by zero or entered something that's not an Int. Try again!")
        println("Info from the exception: " + e.getMessage)
        divide
    }
  }

  def divide2: Try[Int] = {
    def dividend = Try(StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt)
    def divisor = Try(StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt)
    def devide(x: Int, y: Int) = Try(x / y)
    val problem = for {
      x <- dividend
      y <- divisor
      result <- devide(x, y)
    } yield (x, y, result)
    problem match {
      case Success(v) =>
        println("Result of " + v._1 + "/"+ v._2 +" is: " + v._3)
        Success(v._3)
      case Failure(e) =>
        println("You must've divided by zero or entered something that's not an Int. Try again!")
        println("Info from the exception: " + e.getMessage)
        divide
    }
  }

  def divideWithRetry: Try[Int] = {
    val dividend = retry(3)(Try(StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt))
    val divisor = retry(3)(Try(StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt))
    def devide(x: Int, y: Int) = Try(x / y)

    val retryDivisor: Int => Try[Int] = (x: Int) => for {
      y <- divisor
      result <- devide(x, y)
    } yield result

    val problem = for {
      x <- dividend
      result <- retry(3)(retryDivisor(x))
    } yield result
    problem match {
      case Success(v) =>
        println("Result of " + dividend.get + "/"+ divisor.get +" is: " + v)
        Success(v)
      case Failure(e) =>
        println("You must've divided by zero or entered something that's not an Int. Try again!")
        println("Info from the exception: " + e.getMessage)
        divide
    }
  }

  @tailrec
  def retry[T](times : Int = 3, sleepingInMilli: Int = 0)
              (trying: => Try[T]) : Try[T] = {
    trying match {
      case Failure(_) if times > 0 =>
        if(sleepingInMilli > 0) {
          Thread.sleep(sleepingInMilli)
        }
        retry(times - 1, sleepingInMilli)(trying)
      case other => other
    }
  }
}
