package demo.scala
import java.io.{File, FileInputStream}

object LoanPattern {
  def readFile[T](f: File)(handler: FileInputStream => T): T = {
    val resource = new java.io.FileInputStream(f)
    try {
      handler(resource)
    } finally {
      resource.close()
    }
  }

  readFile(new java.io.File("test.txt")) { input =>
    var data = input.read
    while (data != -1) {
      print(data.toChar)
      data = input.read
    }
  }
}
