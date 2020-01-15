package split

case class HomeScreen(user: User) {

  def start: Unit = {
    printHomeScreen
    val input = Tools.takeIntInput(1,user.groups.size + 3, "Välj siffra: ")
    proceed(input)
  }

  def proceed(x: Int): Unit = {
    x match {
      case 1 => Groups.createGroup(user).start
      case 2 => StartScreen.start
      case 3 => { user.printDebts; start }
      case _ if x <= (user.groups.size + 3) && x >= 4 => { user.findGroup(x-4).start }
      case _ =>
    }
  }

  def printHomeScreen: Unit = {
    println(s"\nHemskärm för ${user.name}\n"+ "1. Skapa Ny grupp\n" + "2. Logga ut\n" + "3. Mina skulder\n" + toStringGroups)
  }

  def toStringGroups: String = {
    val names = user.groups.keys.toVector.sorted
    var result = ""
    var nbr = 4
    for (name <- names) {
      result = result + s"$nbr. $name\n"
      nbr += 1
    }
    result
  }
}