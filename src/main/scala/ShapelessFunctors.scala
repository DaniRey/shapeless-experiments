import shapeless._

/**
  * @author Dani
  */
object ShapelessFunctors extends App {
  import functorSyntax._

  sealed trait Tree[T]
  case class Leaf[T](t: T) extends Tree[T]
  case class Node[T](l: Tree[T], r: Tree[T]) extends Tree[T]

  val tree =
    Node(
      Leaf("Reto"),
      Node(
        Leaf("Sandra"),
        Leaf("Mike")
      )
    )

  println(tree)
  println(tree.map(_.length))
}

/**
  * Illustrative subset of the Cats Functor type class
  */
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor extends Functor0 {
  def apply[F[_]](implicit f: Lazy[Functor[F]]): Functor[F] = f.value

  implicit val idFunctor: Functor[Id] =
    new Functor[Id] {
      def map[A, B](a: A)(f: A => B): B = f(a)
    }

  // Induction step for products
  implicit def hcons[F[_]](implicit ihc: IsHCons1[F, Functor, Functor]): Functor[F] =
    new Functor[F] {
      def map[A, B](fa: F[A])(f: A => B): F[B] = {
        val (hd, tl) = ihc.unpack(fa)
        ihc.pack((ihc.fh.map(hd)(f), ihc.ft.map(tl)(f)))
      }
    }

  // Induction step for coproducts
  implicit def ccons[F[_]](implicit icc: IsCCons1[F, Functor, Functor]): Functor[F] =
    new Functor[F] {
      def map[A, B](fa: F[A])(f: A => B): F[B] =
        icc.pack(icc.unpack(fa).fold(hd => Left(icc.fh.map(hd)(f)), tl => Right(icc.ft.map(tl)(f))))
    }

  implicit def generic[F[_]](implicit gen: Generic1[F, Functor]): Functor[F] =
    new Functor[F] {
      def map[A, B](fa: F[A])(f: A => B): F[B] =
        gen.from(gen.fr.map(gen.to(fa))(f))
    }
}

trait Functor0 {
  implicit def constFunctor[T]: Functor[Const[T]#λ] =
    new Functor[Const[T]#λ] {
      def map[A, B](t: T)(f: A => B): T = t
    }
}

// Functor syntax
object functorSyntax {
  implicit def apply[F[_]: Functor, A](fa: F[A]): FunctorOps[F, A] =
    new FunctorOps[F, A](fa)

  class FunctorOps[F[_], A](fa: F[A])(implicit F: Functor[F]) {
    def map[B](f: A => B): F[B] = F.map(fa)(f)
  }
}
