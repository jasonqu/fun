package demo.scala.cats

import cats._
import cats.data.Validated
import cats.implicits._

object FailFast extends App {

  case class User(name: String, age: Int)

  def validateUserRF(user : User): Either[String, User] = {
    if(user.name.isEmpty) {
      return "name empty".asLeft
    }

    if(user.age <= 0) {
      return "negative age".asLeft
    }

    user.asRight
  }

  def validateUserFF(user: User): Either[String, User] =
    user.asRight[String]
      .ensure("name empty")(_.name.nonEmpty)
      .ensure("negative age")(_.age > 0)




  def validateUser(user: User): Validated[List[String], User] = {
    val original = user.valid[List[String]]
    (original.ensure(List("name empty"))(_.name.nonEmpty),
      original.ensure(List("negative age"))(_.age > 0))
      .mapN((a, _) => a)
  }

  // act as Either
//  def validateUser(user: User): Validated[List[String], User] =
//    user.valid[List[String]]
//      .ensure(List("name empty"))(_.name.nonEmpty)
//      .ensure(List("negative age"))(_.age > 0)





  val user = User("", 0)
  println(validateUserRF(user))
  println(validateUserFF(user))
  println(validateUser(user))

}
