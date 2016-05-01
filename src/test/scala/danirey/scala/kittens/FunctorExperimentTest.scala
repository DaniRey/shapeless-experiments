package danirey.scala.kittens

import cats.Functor
import cats.syntax.AllSyntax
import cats.derived.functor._
import legacy._
import cats.derived.iterable.legacy._
import org.scalatest.FunSuite
import org.typelevel.discipline.scalatest.Discipline
import shapeless.cachedImplicit

/**
  * @author Dani
  */
class FunctorExperimentTest extends FunSuite with Discipline with AllSyntax {

  import AdtDefns._

  test("functors experiment") {
    val funct = Functor[Tree]

    val tree: Tree[String] = Node(
        Leaf("Reto"),
        Node(
            Leaf("Sandra"),
            Leaf("Mike")
        )
    )

    println(funct.map(tree)(_.length))
  }
}
