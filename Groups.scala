package split

object Groups {
  var all: Vector[Group] = Vector()

  def createGroup(user: User): Group = {
    val name = Tools.takeStringInput("Gruppnamn: ")
    val group = Group(name)
    group.add(user.number)
    all = all :+ group
    user.addGroup(group)
    group
  }
}
