package demo.scala.cats.zio

import zio._
import zio.console._

object ZioTest extends zio.App{
  def readInt(message: String): ZIO[Console, Throwable, Int] = for {
    _ <- putStr(message)
    name <- getStrLn
    v <- ZIO.effect(name.toInt)
  } yield v

  def divide(a: Int, b: Int): Task[Int] =
    ZIO.effect(a / b)

  val process: ZIO[Console, Throwable, Unit] = {
    for {
      _ <- putStrLn("welcome")
      (dividend, divisor) <- readInt("Enter an Int that you'd like to divide:") zip
        readInt("Enter an Int that you'd like to divide by:")
      result <- divide(dividend, divisor)
      _ <- putStrLn(s"result is $result")
    } yield ()
  }

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    process.exitCode
}
