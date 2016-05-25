package danirey.scala.kittens

/**
  * @author Dani
  */
import cats.Functor
import AdtDefns._

object Kittens extends App {
    val funct = Functor[Tree]
    val tree: Tree[String] = Node(
        Leaf("Reto"),
        Node(
            Leaf("Sandra"),
            Leaf("Mike")
        )
    )

  //println(Tree.f.map(tree)(_.length))
    println(funct.map(tree)(_.length))
}
