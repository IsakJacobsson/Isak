package split

case class User(number: String, name: String) {
  var debts: Map[User, Double] = Map()
  var groups: Map[String, Group] = Map()

  def printDebts: Unit = {
    for (debt <- debts) {
      println(s"${debt._1.name}: ${debt._2} kr")
    }
    if (debts.size == 0) {
      println("Du har inga skulder! :)")
    }
  }

  def addDebt(user: User, amount: Double): Unit = {
    debts = debts.updated(user, debts.getOrElse(user, 0.0) + amount)
  }

  def addGroup(group: Group): Unit = {
    groups = groups.updated(group.name, group)
  }

  var contacts: Map[String, User] = Map()
  def contactByName: Map[String, User] = {
    var result: Map[String, User] = Map()
    for (contact <- contacts) {
      result = result.updated(contact._2.name, contact._2)
    }
    result
  }
  def contactNames: Vector[String] = contactByName.keys.toVector.sorted
  def nbrOfContacts: Int = contactNames.size

  def findGroup(x: Int): Group = {
    val name = groups.keys.toVector.sorted.apply(x)
    groups(name)
  }
}
