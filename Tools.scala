package split

import scala.util.{Success, Try}

object Tools {
  def takeIntInput(from: Int, to: Int, msg: String): Int = {
    var ok = false
    var number = -1
    while (!ok) {
      val tryNumber = Try { scala.io.StdIn.readLine(msg).toInt }
      tryNumber match {
        case Success(x) if x >= from && x <= to => { ok = true; number = x }
        case _ =>
      }
    }
    number
  }

  def takeStringInputOfLength(length: Int, msg: String): String = {
    var ok = false
    var text = ""
    while (!ok) {
      text = scala.io.StdIn.readLine(msg)
      if (text.length == length) ok = true
    }
    text
  }

  def takeStringInput(msg: String): String = {
      scala.io.StdIn.readLine(msg)
  }

  def isUser(x: String): Boolean = {
    Users.all.isDefinedAt(x)
  }
}
