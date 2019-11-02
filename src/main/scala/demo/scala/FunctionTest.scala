package demo.scala

object FunctionTest extends App {
  val sum = (a: Int, b: Int) => a + b

  val sum2 = new Function2[Int, Int, Int] {
    def apply(x: Int, y: Int): Int = x + y
  }

  def add(a: Int, b: Int): Int = a + b
  val sumFromMethod = add _

  val plusOne = (a: Int) => a + 1
  val multiplyByTwo = (a: Int) => a * 2
  val composed = plusOne andThen multiplyByTwo
  println(composed(2))

  // partial applied function
  val sum5 : (Int, Int, Int, Int, Int) => Int =
    (a, b, c, d, e) => a + b + c + d + e
  val add6: (Int, Int) => Int = sum5(2, _, 3, 1, _)
  println(add6(4, 3))

  // Currying
  val sum3 : (Int, Int, Int) => Int = (a, b, c) => a + b + c
  val add2: Int => Int => Int = sum3.curried(2)
  println(add2(4)(3))



}
