import shapeless.HNil

/**
  * @author Dani
  */
class Experiments {
  val person = "Dani" :: 1.90 :: HNil

  def mayPass() = person.tail.head < 2.0

  def greet(): Unit = println("Hello " + person.head)
}
