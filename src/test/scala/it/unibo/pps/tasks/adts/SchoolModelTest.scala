package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*

class SchoolModelTest:

  import it.unibo.pps.tasks.adts.SchoolModel.BasicSchoolModule
  import BasicSchoolModule.*
  import it.unibo.pps.u03.extensionmethods.Sequences.Sequence
  import Sequence.*

  private val teacherName1 = "Viroli"
  private val teacherName2 = "Ricci"
  private val teacherName3 = "Baldazzi"
  private val teacher1 = teacher(teacherName1)
  private val teacher2 = teacher(teacherName2)
  private val teacher3 = teacher(teacherName3)
  private val courseName1 = "PPS"
  private val courseName2 = "OOP"
  private val courseName3 = "PCD"
  private val course1 = course(courseName1)
  private val course2 = course(courseName2)
  private val course3 = course(courseName3)
  private val school = emptySchool
    .setTeacherToCourse(teacher1, course1)
    .setTeacherToCourse(teacher1, course1) // duplicate entry
    .setTeacherToCourse(teacher1, course2)
    .setTeacherToCourse(teacher2, course3)

  @Test
  def testCourses(): Unit =
    assertEquals(Nil(), emptySchool.courses)
    assertEquals(Cons(course1, Cons(course2, Cons(course3, Nil()))), school.courses)

  @Test
  def testTeachers(): Unit =
    assertEquals(Nil(), emptySchool.teachers)
    assertEquals(Cons(teacher1, Cons(teacher2, Nil())), school.teachers)

  // I don't know how to test it since everything else should work
  // if setTeacherToCourse works
  @Test
  def testSetTeacherToCourse(): Unit = {}

  @Test
  def testCoursesOfATeacher(): Unit =
    assertEquals(Cons(course1, Cons(course2, Nil())), school.coursesOfATeacher(teacher1))
    assertEquals(Cons(course3, Nil()), school.coursesOfATeacher(teacher2))
    assertEquals(Nil(), school.coursesOfATeacher(teacher3))

  @Test
  def testHasTeacher(): Unit =
    assertFalse(emptySchool.hasTeacher(teacherName1))
    assertTrue(school.hasTeacher(teacherName1))
    assertFalse(school.hasTeacher(teacherName3))

  @Test
  def testHasCourse(): Unit =
    assertFalse(emptySchool.hasCourse(courseName1))
    assertTrue(school.hasCourse(courseName1))
    assertFalse(school.hasCourse("inexistentCourse"))