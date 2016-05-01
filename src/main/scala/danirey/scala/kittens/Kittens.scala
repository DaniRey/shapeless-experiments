package danirey.scala.kittens

/**
  * @author Dani
  */
import cats.Functor
import cats.syntax.AllSyntax
import cats.derived.functor._
import legacy._
import cats.derived.iterable.legacy._
import org.typelevel.discipline.scalatest.Discipline
import shapeless.cachedImplicit

object Kittens extends App {
  val ft = new FunctorExperiment()
//  ft.print()
}

class FunctorExperiment extends AllSyntax {
  import AdtDefns._

  def print():Unit = {
    /*fails with
[error] D:\repos\shapeless-experiments\src\main\scala\danirey\scala\kittens\Kittens.scala:23: could not find implicit value for parameter instance: cats.Functor[danirey.scala.kittens.AdtDefns.Tree]
[error]     val funct = Functor[Tree]
when
sbt clean
sbt compile
--
works when
commented out
sbt clean
sbt compile
commenting removed
sbt package
can be executed after successfully compiling
     */
//    val funct = Functor[Tree]
//    val tree: Tree[String] = Node(
//        Leaf("Reto"),
//        Node(
//            Leaf("Sandra"),
//            Leaf("Mike")
//        )
//    )
//
//    println(funct.map(tree)(_.length))
  }
}
