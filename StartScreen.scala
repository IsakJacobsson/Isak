package split

object StartScreen {
  def printStartScreen: Unit = {
    println("\nHome Screen\n" +
            "1. Logga in\n" +
            "2. Skapa Användare\n" +
            "3. Avsluta")
  }

  def proceed(x: Int): Unit = {
    x match {
      case 1 => LogIn.start
      case 2 => CreateUser.start
      case 3 => System.exit(1)
      case _ =>
    }
  }

  def start: Unit = {
    printStartScreen
    val input = Tools.takeIntInput(1,3, "Välj siffra: ")
    proceed(input)
  }

}
