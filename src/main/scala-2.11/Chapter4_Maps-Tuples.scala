import java.io.File
import scala.io.Source
import scala.collection.JavaConversions.mapAsScalaMap
import java.util.Calendar._

import scala.util.Random

/**
 * Created by barn on 10/11/15.
 */

object Chapter4_Maps_Tuples extends App {

  //Map
  val wishList: Map[String, Double] = Map(
    "Xbox One" -> 300.00,
    "Climbing Rope" -> 100.00,
    "Climbing Harnass" -> 45.00,
    "Bannana" -> 0.79,
    ("Cheese", 5))
  println(wishList)

  //For yields a new list mapping k -> v (v has 10% discount).
  val discount = for ((k, v) <- wishList) yield (k, v * 0.9)
  println(discount)

  /**
   * 2- SCALA Method which reads a file using Scala.IO.
   */
  def readFileScalaIO = {
      val source = Source.fromFile("/home/barn/Documents/myfile.txt", "UTF-8") //Don't have to include encoding type
      val tokens = source.mkString.split("\\s+") //Split the text file at each space OR many spaces.

      //Following line of code is found on page 44 of Scala for Impatient. Interoperating with Java. Using TreeMap which scala doesn't provide
      val freq: scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
    //    for each token (each word) map the word to its frequency
         tokens foreach { token =>
            freq(token) = freq.getOrElse(token, 0) + 1
         }
      println(freq)
  }

  /**
   * 2 - Read words from a file, Use mutable map to count how often each word appears
   *  Same as above but using Java implementation
   */
  def readFileJavaIO = {
      val in = new java.util.Scanner(new java.io.File("/home/barn/Documents/myfile.txt"))
      val wordCount = scala.collection.mutable.Map[String, Int]() withDefault (_ => 0) //Originally maps all inputs to count 0.
           while (in.hasNext) wordCount(in next) += 1
    println(wordCount)
}

//  3 - Same as above but make the list immutable
  def readFileScalaIOImmutable = {
    val source = Source.fromFile("/home/barn/Documents/myfile.txt", "UTF-8") //Don't have to include encoding type
    val tokens = source.mkString.split("\\s+") //Split the text file at each space OR many spaces.

    //Following line of code is found on page 44 of Scala for Impatient. Interoperating with Java. Using TreeMap which scala doesn't provide
    var freq = new scala.collection.immutable.HashMap[String, Int]
    //    for each token (each word) map the word to its frequency
    tokens foreach { token =>
      freq = freq + (token -> (freq.getOrElse(token, 0) + 1) )
    }
    println(freq)
  }

//  4 - Print sorted list
    val in = new java.util.Scanner(new java.io.File("/home/barn/Documents/myfile.txt"))
    var wordCounts = collection.immutable.SortedMap[String, Int]() withDefault (_ => 0)
      while (in hasNext) {
    val key = in next
    val currentCount = wordCounts(key) //initially be 0. Increments each time another word is found
    wordCounts = wordCounts - key + (key -> (currentCount + 1))
  }
  println("Sorted: " + wordCounts)

  /**
   * 6 - Linked Hash Map
   */
  val days = scala.collection.mutable.LinkedHashMap(
    "Monday"    -> java.util.Calendar.MONDAY,
    "Tuesday"   -> TUESDAY,
    "Wednesday" -> WEDNESDAY,
    "Thursday"  -> THURSDAY,
    "Friday"    -> FRIDAY,
    "Saturday"  -> SATURDAY,
    "Sunday"    -> SUNDAY
  )
  for ((key, value) <- days)  printf("%s=%d\n", key, value)

  /**
   * Print out a table of all Java properties
   */
  val properties = scala.collection.JavaConversions.propertiesAsScalaMap(System getProperties())
  val maxKey = properties.keySet.map(_.length).max //Must find length of longest key so printf table format works
  for((key, value) <- properties) printf("%-" + maxKey + "s | %s\n", key, value)

  /**
   * Create and populate an array with Random (n) integers! GOOD CODE
   */
  def randArray(n: Int): Array[Int] = {
    val randArr = new Array[Int](n)
    for(i <- 0 until randArr.length) randArr(i) = Random.nextInt(n)
    randArr
  }
  val array = randArray(50)
//  for(i <- array) println(i)

  /**
   *  Returning a tuple min max
   */
  def minmax(values: Array[Int]): Tuple2[Int, Int] = {
    val min = values.min
    val max = values.max
    (min, max)
  }
  println(minmax(array))

  /**
   *  Counting occurances in an Array using the count function with the _
   */
  def countValuesInArray(values: Array[Int], v: Int): Tuple3[Int, Int, Int] = {
     (values.count(_ < v), values.count(_ > v), values.count(_ == v))
  }
  println(countValuesInArray(array, 30))

  /**
   * Example of zipping. Creates Vector/Tuple mapping corresponding characters. EG (H,W)
   */
  val h = "Hello"
  val w = "world"
  println(h.zip(w))

}


