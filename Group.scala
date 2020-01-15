package split

import scala.util.{Failure, Success, Try}

case class Group(name: String) {
  var members: Map[String, User] = Map()

  def add(number: String): Unit = {
    val user = Users.all(number)
    members = members.updated(user.name, user)
    user.addGroup(this)
  }

  def start: Unit = {
    printGroup
    val input = chooseNumbers
    proceed(input)
  }

  def proceed(xs: Vector[Int]): Unit = {
    if (xs.length == 1) {
      if (xs(0) == 1) {
        var ok = false
        var number = ""
        while (!ok) {
          number = Tools.takeStringInputOfLength(10, "Telefonnummer: ")
          if (Tools.isUser(number)) ok = true
        }
        add(number)
        start
      }
      else if (xs(0) == members.size + 2) HomeScreen(LogIn.loggedInAs).start
      else { SplitWith(Vector(findUser(xs(0)-2))).start; start }
    }
    else if (!xs.exists(x => x < 1 || x > members.size + 1)) {
      val numbers = xs.toSet
      var splitBetween: Vector[User] = Vector()
      for (number <- numbers) {
        splitBetween = splitBetween :+ findUser(number - 2)
      }
      SplitWith(splitBetween).start
      start
    }
    else {
      println("ERROR: något gick fel")
      start
    }
  }

  def chooseNumbers: Vector[Int] = {
    val input = Tools.takeStringInput("Splitta med: ").split(' ').toVector
    var result: Vector[Int] = Vector()
    for (x <- input) {
      Try{ result = result :+ x.toInt } match {
        case Failure(i) => { println(s"ERROR: $x är inte en siffra"); start }
        case Success(i) if x.toInt >= 1 && x.toInt <= (members.size + 2) =>
        case _ => { println("ERROR: något gick fel"); start }
      }
    }
    result
  }

  def printGroup: Unit = {
    println(s"\nGrupp $name\n" + "1. Lägg till\n" + toStringMembers + s"${members.size + 2}. Gå tillbaka")
  }

  def toStringMembers: String = {
    var result = ""
    var nbr = 2
    val memberNames = members.keys.toVector.sorted
    for (name <- memberNames) {
      result = result + s"$nbr. $name\n"
      nbr += 1
    }
    result
  }

  def findUser(x: Int): User = {
    val name = members.keys.toVector.sorted.apply(x)
    members(name)
  }
}
