package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.u03.Optionals.Optional
import it.unibo.pps.u03.Optionals.Optional.*
import it.unibo.pps.u03.Sequences.Sequence
import it.unibo.pps.u03.Sequences.Sequence.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a data structure T[A]
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: " + a)

  trait Traversable[T[_]]:
    def logAll[A](trav: T[A]): Unit

  object Traversable:
    extension [T[_] : Traversable, A](trav: T[A])
      def logAll(): Unit = summon[Traversable[T]].logAll(trav)

    given Traversable[Sequence] with
      def logAll[A](seq: Sequence[A]): Unit = seq match
        case Cons(h, t) => log(h); logAll(t)
        case _ => ()

    given Traversable[Optional] with
      def logAll[A](opt: Optional[A]): Unit = opt match
        case Just(a) => println(s"The element is: $a")
        case Empty() => println("The optional is empty")

  @main def tryTraversables(): Unit =
    import Traversable.{*, given}
    val s1 = Cons(3, Cons(2, Cons(1, Nil())))
    s1.logAll()
    val s2 = Cons("first", Cons("second", Cons("third", Nil())))
    s2.logAll()
    val opt1 = Just(5)
    opt1.logAll()
    val opt2 = Just("5")
    opt2.logAll()
    val opt3 = Empty()
    opt3.logAll()
