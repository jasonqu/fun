package demo.scala.cats

object ForComprehensions extends App {

  def foo(n: Int, v: Int) =
    for (i <- 0 until n;
         j <- 0 until n if i + j == v)
      yield (i, j)

  foo(10, 10) foreach {
    case (i, j) =>
      println(s"($i, $j) ") // prints (1, 9) (2, 8) (3, 7) (4, 6) (5, 5) (6, 4) (7, 3) (8, 2) (9, 1)
  }


  def fooTranslate(n: Int, v: Int) =
    (0 until n).flatMap { i =>
      (0 until n).withFilter(j => i + j == v)
        .map { j =>
          (i, j)
        }
    }



}
