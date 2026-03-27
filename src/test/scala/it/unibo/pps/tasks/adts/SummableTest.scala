package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*

class SummableTest:

  import it.unibo.pps.u03.Sequences.Sequence
  import Sequence.*
  import it.unibo.pps.tasks.typeclasses.Ex4Summables.*

  @Test def testSumAllInt(): Unit =
    val si = Cons(10, Cons(20, Cons(30, Nil())))
    assertEquals(60, sumAllInt(si))

  @Test def testSumAll(): Unit =
    val si = Cons(10, Cons(20, Cons(30, Nil())))
    assertEquals(60, sumAll(si))
    val sd = Cons(10.0, Cons(20.0, Cons(30.0, Nil())))
    assertEquals(60.0, sumAll(sd), 0.0)
    val ss = Cons("10", Cons("20", Cons("30", Nil())))
    assertEquals("102030", sumAll(ss))
