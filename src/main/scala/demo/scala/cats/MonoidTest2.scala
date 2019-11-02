package demo.scala.cats
import cats.Monoid

object MonoidTest2 extends App {

  case class StatValue(requests: Int, failures: Int, avgTime: Double, maxTime: Long) {
    lazy val str = s"$requests, $failures, $avgTime, $maxTime"
  }

  object StatValueMoniod {
    implicit val statValueMonoid: Monoid[StatValue] = new Monoid[StatValue] {
      def combine(a: StatValue, b: StatValue) =
        StatValue(
          a.requests + b.requests,
          a.failures + b.failures,
          // todo buggy empty + empty
          (a.avgTime * a.requests + b.avgTime * b.requests) / (a.requests + b.requests),
          if (b.maxTime > a.maxTime) b.maxTime else a.maxTime)

      def empty = StatValue(0, 0, 0, 0)
    }
  }
}
