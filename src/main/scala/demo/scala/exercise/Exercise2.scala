package demo.scala.exercise

import scala.collection.mutable
import scala.util.Random

object Exercise2 extends App {
  // 1 1+2+3+ …… +100=？
  val r = 1 to 100


  /*
  var sum = 0
  for(i <- r) {
    sum += i
  }
  println(sum)

  println(r.foldLeft(0)(_ + _))
  println(r.sum)
  */

  // 求一组整数中，所有为偶数的，且出现次数排前三的任意3个数字。
  val rand = new Random()
  val bag = List.fill(20)(rand.nextInt(10))
  println(bag)







  /*
  val result = bag.filter(_ % 2 == 0)
    .groupBy(identity).mapValues(_.size).toList
    .sortWith(_._2 > _._2)
    .take(3)
  println(result)

  val stats = mutable.Map[Int, Int]()
  for {
   i<- bag if i % 2 == 0
  } {
    stats.put(i, stats.getOrElse(i, 0) + 1)
  }
  println(stats.toList.sortWith(_._2 > _._2).take(3))



   */
}
