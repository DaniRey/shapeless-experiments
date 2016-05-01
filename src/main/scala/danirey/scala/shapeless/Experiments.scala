package danirey.scala.shapeless

/**
  * @author Dani
  */
import shapeless._

object Experiments extends App {

  def mayPass() = person.tail.head < 2.0

  def greet(): Unit = println("Hello " + person.head)

  object plus extends Poly2 {
    implicit val caseIntInt = at[Int, Int](_ + _)
    implicit val caseIntString = at[Int, String](_ + _.length)
    implicit val caseIntDouble = at[Int, Double](_ + _.toInt)
    implicit val caseIntBoolean = at[Int, Boolean]((i, b) => i + 1)
  }

  object size extends Poly1 {
    implicit def default[T] = at[T](_ => 1)
    implicit def caseInt = at[Int](_ => 1)
    implicit def caseString = at[String](_.length)
    implicit def caseList[T] = at[List[T]](_.length)
    implicit def caseOption[T](implicit st: Case.Aux[T, Int]) = at[Option[T]](t => 1 + (t map size).getOrElse(0))
    implicit def caseTuple[T, U](implicit st: Case.Aux[T, Int], su: Case.Aux[U, Int]) = at[(T, U)] { case (t, u) => size(t) + size(u) }
  }

  val person = "Dani" :: 1.90 :: HNil
  println(person)
  println("sum of person " + person.foldLeft(0)(plus))
  println("sizes " + person.map(size).toList.sum)
}
