package demo.scala.cats

// 类型类自身是我们希望实现的某种功能的API接口
trait Show[T] {
  def show(t: T): String
}

// 类型类实例是我们期望实现该功能的目标，一般定义为隐式
case class Cat(name:String, gender:String)

// 类型类实例是我们期望实现该功能的目标，一般定义为隐式
object Cat {
  implicit val catShow = new Show[Cat] {
    override def show(t: Cat): String = s"${t.name} is a ${t.gender} cat, meow..."
  }
}

// 最后式暴露给用户的接口，般是泛型方法，接受隐式对象，来将功能应用在某种类型上
object Show {
  def getDescription[T: Show](t: T) :String =
    implicitly[Show[T]].show(t)
}

object TypeClassExample extends App {
  val cat = Cat("Garfield", "male")
  println(cat)
  println(Show.getDescription(cat))

  implicit val catShowNameOnly = new Show[Cat] {
    override def show(t: Cat): String = s"${t.name} is a cat, meow..."
  }
  println(Show.getDescription(cat))

  import ShowSyntax._
  println(cat.description)
}

// 可以通过增加Syntax的隐式类来实现 对现有类型的语法糖
object ShowSyntax {
  implicit class ShowOps[T](t: T) {
    def description(implicit w: Show[T]):String = w.show(t)
  }
}
