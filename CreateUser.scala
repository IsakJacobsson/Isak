package split

object CreateUser {
  def start: Unit = {
    val name = Tools.takeStringInput("Namn: ")
    val phoneNumber = getPhoneNumber
    val user = User(phoneNumber, name)
    Users.all = Users.all.updated(phoneNumber, user)
    StartScreen.start
  }

  def isPhoneNumber(xs: String): Boolean = {
    !xs.exists(!_.isDigit)
  }

  def getPhoneNumber: String = {
    var ok = false
    var phoneNumber = ""
    while (!ok) {
      phoneNumber = Tools.takeStringInputOfLength(10, "Telefonnummer: ")
      if (isPhoneNumber(phoneNumber)) ok = true
    }
    phoneNumber
  }
}
