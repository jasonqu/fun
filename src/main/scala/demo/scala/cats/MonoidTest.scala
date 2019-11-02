package demo.scala.cats

import cats._
import cats.implicits._

object MonoidTest extends App {
  implicit val intAddMonoid: Monoid[Int] =
    new Monoid[Int] {
      def combine(a: Int, b: Int): Int = a + b
      def empty = 1
    }

  implicit val stringConcatMonoid: Monoid[String] =
    new Monoid[String] {
      def combine(a: String, b: String): String = a + b
      def empty = ""
    }

  // intMultiply
  // booleanAnd

  implicit def listMonoid[A]: Monoid[List[A]] =
    new Monoid[List[A]] {
      def combine(a: List[A], b: List[A]): List[A] = a ::: b
      def empty: List[A] = Nil
    }

  implicit def mapMonoid[K, V: Monoid]: Monoid[Map[K, V]] =
    new Monoid[Map[K, V]] {
      val vmonoid: Monoid[V] = implicitly[Monoid[V]]
      val vzero: V = vmonoid.empty
      def combine(a: Map[K, V], b: Map[K, V]): Map[K, V] =
        (a.keySet ++ b.keySet).foldLeft(empty) { (acc, k) =>
          acc.updated(k,
            vmonoid.combine(
              a.getOrElse(k, vzero),
              b.getOrElse(k, vzero))
          )
        }
      def empty: Map[K, V] = Map[K, V]()
    }

  val map1 = Map("a" -> 1, "b" -> 2)
  val map2 = Map("b" -> 3, "d" -> 4)

  println(map1 |+| map2)
}
