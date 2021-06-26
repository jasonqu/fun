package demo.scala.list

object ListTest extends App {
  val stack: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
  val stack2: List[Int] = List(1, 2, 3)
  //List.setHead(stack, 0)

  List.printList(stack)
  List.setHead(stack, 0)
  List.printList(stack)

  // 应用场景
  // 有一堆英文句子
  val listOfWords = List(
    "His life story is recounted in two fascinating volumes of autobiography.",
    "New facts were brought to light by scholars.",
    "to be or not to be?",
    "This evidence did not come to light until after the trial.",
    "Are you able to shed any light on this subject?")

  // map 映射成为单词的集合
  val splitStringToWordArray = (a:String) => a.split(" ")
  val listOfArray = List.map(listOfWords, splitStringToWordArray)
  List.printList(listOfArray)

  // filter，找出字符数大于20的句子
  val stringLengthBiggerThan20: String => Boolean =
    (a:String) => a.length > 20
  // Partial Applied Function
  val filterLengthBiggerThan20: List[String] => List[String] =
    List.filter[String](_, stringLengthBiggerThan20)

  List.printList(filterLengthBiggerThan20(listOfWords))

  // reduce fold
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
  }
  println(sum(List(1, 2, 3)))

  // 求出所有句子的单词数总和
  def wc(strs: List[Array[String]]): Int = strs match {
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x.length + wc(xs)
  }
  println(wc(listOfArray))

  // 通用部分
  List.foldRightUncurry[Int, Int](List(1, 2, 3), 0, _ + _)
  List.foldRightUncurry[Array[String], Int](listOfArray, 0, _.length + _)

  // curry form
  List.foldRight(List(1, 2, 3), 0)(_ + _)
  List.foldRight(listOfArray, 0) {(arr, sum) =>
    arr.length + sum
  }
}
