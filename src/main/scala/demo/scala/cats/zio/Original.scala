package demo.scala.cats.zio

import cats.Id
import cats.implicits._

import scala.io.StdIn

object Original extends App {
  def display(display: String): Unit = {
    println(display)
  }
  def readFromConsole(): String = {
    StdIn.readLine()
  }

  display("Enter an Int that you'd like to divide:")
  val dividend = readFromConsole().toInt
  display("Enter an Int that you'd like to divide by:")
  val divisor = readFromConsole().toInt
  val result = dividend / divisor
  display(s"result is $result")
}


// 改造为不带变量
object NoVals extends App{
  val display: String => Unit = (display: String) => {
    println(display)
  }
  val readFromConsole: Any => String = (any:Any) => {
    StdIn.readLine()
  }
  val convertToInt: String => Int = (str: String) => {
    str.toInt
  }
  val divide : (Int, Int) => Int = _ / _

  display("result is " +
    divide(
      convertToInt(
        readFromConsole(
          display("Enter an Int that you'd like to divide:")
        )
      ),
      convertToInt(
        readFromConsole(
          display("Enter an Int that you'd like to divide by:")
        )
      )
    )
  )
}

// 改造为不带变量
object NoVals2 extends App{
  val display: String => Id[Unit] = (display: String) => {
    println(display)
  }

  // todo 使用这个case class是因为string本身也可以被当做一个有flatmap方法的对象
  case class StrValue(str: String)
  val readFromConsole: Any => Id[StrValue] = (any: Any) => {
    StrValue(StdIn.readLine())
  }
  val convertToInt: String => Id[Int] = (str: String) => {
    str.toInt
  }
  val divide : (Int, Int) => Id[Int] = (a: Int, b: Int) => a / b


  display("Enter an Int that you'd like to divide:").flatMap { _ =>
    readFromConsole(()).flatMap { dividendStr =>
      convertToInt(dividendStr.str).flatMap { dividend =>
        display("Enter an Int that you'd like to divide by:").flatMap { _ =>
          readFromConsole(()).flatMap { divisorStr =>
            convertToInt(divisorStr.str).flatMap { divisor =>
              divide(dividend, divisor).flatMap { result =>
                display(s"result is $result")
                  .map(_ => ())
              }
            }
          }
        }
      }
    }
  }
}


// 改造为不带变量
object NoValsFor extends App{
  val display: String => Id[Unit] = (display: String) => {
    println(display)
  }

  // todo 使用这个case class是因为string本身也可以被当做一个有flatmap方法的对象
  case class StrValue(str: String)
  val readFromConsole: Any => Id[StrValue] = (any: Any) => {
    StrValue(StdIn.readLine())
  }
  val convertToInt: String => Id[Int] = (str: String) => {
    str.toInt
  }
  val divide : (Int, Int) => Id[Int] = (a: Int, b: Int) => a / b

  for {
    _ <- display("Enter an Int that you'd like to divide:") : Id[Unit]
    dividendStr <- readFromConsole(())
    dividend <- convertToInt(dividendStr.str)
    _ <- display("Enter an Int that you'd like to divide by:")
    divisorStr = readFromConsole()
    divisor <- convertToInt(divisorStr.str)
    result <- divide(dividend, divisor)
    _ <- display(s"result is $result")
  } yield ()
}






// 改造为不带变量
object NoValsFor2 extends App{
  val display: String => Id[Unit] = (display: String) => {
    println(display)
  }

  // todo 使用这个case class是因为string本身也可以被当做一个有flatmap方法的对象
  case class StrValue(str: String)
  val readFromConsole: Any => Id[StrValue] = (any: Any) => {
    StrValue(StdIn.readLine())
  }
  val convertToInt: String => Id[Int] = (str: String) => {
    str.toInt
  }
  val divide : (Int, Int) => Id[Int] = (a: Int, b: Int) => a / b

  for {
    _ <- display("Enter an Int that you'd like to divide:") : Id[Unit]
    dividendStr <- readFromConsole(())
    dividend <- convertToInt(dividendStr.str)
    _ <- display("Enter an Int that you'd like to divide by:")
    divisorStr = readFromConsole()
    divisor <- convertToInt(divisorStr.str)
    result <- divide(dividend, divisor)
    _ <- display(s"result is $result")
  } yield ()
}