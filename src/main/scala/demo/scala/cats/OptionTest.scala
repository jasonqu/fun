package demo.scala.cats

object OptionTest extends App {
  // from https://www.scalawithcats.com/dist/scala-with-cats.html#what-is-a-monad
  def parseInt(str: String): Option[Int] =
    scala.util.Try(str.toInt).toOption

  def divide(a: Int, b: Int): Option[Int] =
    if(b == 0) None else Some(a / b)

  def stringDivideBy(aStr: String, bStr: String): Option[Int] =
    parseInt(aStr).flatMap { aNum =>
      parseInt(bStr).flatMap { bNum =>
        divide(aNum, bNum)
      }
    }

  def stringDivideByForComp(aStr: String, bStr: String): Option[Int] =
    for {
      aNum <- parseInt(aStr)
      bNum <- parseInt(bStr)
      ans  <- divide(aNum, bNum)
    } yield ans

  println(stringDivideBy("6", "2"))
  println(stringDivideBy("6", "0"))
  println(stringDivideBy("6", "foo"))
  println(stringDivideBy("bar", "2"))
}
