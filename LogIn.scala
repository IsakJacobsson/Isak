package split

object LogIn {
  def start: Unit = {
    println("\nLogga In")
    val user = getUser
    loggedInAs = user
    HomeScreen(user).start
  }

  var loggedInAs: User = User("0000000000", "StartUpUser")

  def getUser: User = {
    var phoneNumber = ""
    while (!Tools.isUser(phoneNumber)) {
      phoneNumber = Tools.takeStringInputOfLength(10,"Telefonnummer: ")
    }
    Users.all(phoneNumber)
  }
}
