package demo.scala.exercise

object Exercise extends App {
  // 1 函数组合
  def compose[A,B,C](f: B => C, g: A => B): A => C = ???

  val plusOne = (a: Int) => a + 1
  val multiplyByTwo = (a: Int) => a * 2
  val composed = compose(plusOne, multiplyByTwo)
  println(composed(2))
}
