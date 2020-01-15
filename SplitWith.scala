package split

case class SplitWith(users: Vector[User]) {
  def start: Unit = {
    var amount = 0
    if (users.length == 1) {
      amount = Tools.takeIntInput(0, Int.MaxValue,"Skuld: ")
      users(0).addDebt(LogIn.loggedInAs, amount)
    }
    else {
      amount = Tools.takeIntInput(0, Int.MaxValue, "Delar på: ")
      val debt: Double = amount / users.length
      val gotToPay = users.filterNot(_ == LogIn.loggedInAs)
      gotToPay.foreach(_.addDebt(LogIn.loggedInAs, debt))
    }
  }
}
