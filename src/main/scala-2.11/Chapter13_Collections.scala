import scala.collection.immutable.{TreeSet, HashMap}
import scala.collection.mutable

object Chapter13_Collections extends App {

  /**
   * 1
   */

  def mapIndex(s: String) = {
    import scala.collection.mutable.HashMap
    import scala.collection.mutable.TreeSet
    val result = new HashMap[Char, TreeSet[Int]]

    s.zipWithIndex.map((ci: (Char, Int)) =>
        result(ci._1) = result.getOrElse(ci._1, new TreeSet[Int]) + ci._2)
    result
  }
//  println(mapIndex("Missippi"))

  /**
   * 2
   */
  def indexes(s: String) = {
    val map = s.zipWithIndex.groupBy(x => x._1).map(x => (x._1, x._2.map(_._2).toList))
    val newMap = map.toSeq.sortBy(_._1).filter(x => x._2.length <= 2)
    map
  }
//  println(indexes("hello mate"))

  /**
   * 3 - Function to remove all zeros from a list of Integers
   */
  val listy = List(1, 3, 4, 5, 7, 0, 0, 0, 0, 0, 4, 2, 0)
//Example using recursion to remove all 0s.
  def removeZeros(x: List[Int]): List[Int] = x match {
    case Nil => Nil
    case h :: t => if(h != 0) h :: removeZeros(t) else removeZeros(t)   //h :: t - (h=head t=tail) create list with tail ELSE just pass the tail in. Forget about current element.
  }
//Example of using foldLeft to remove 0s from the List. Initialise an empty List for foldLeft to use.
  def filterZero(x: List[Int]) = x.foldLeft(List[Int]())((returnList, currentElem) =>
      if(currentElem != 0)  returnList :+ currentElem else returnList)

//  println(filterZero(listy))


  val r = List(1 , 2, 3, 4, 5, 6, 7, 8)

  /**
   * EXAMPLE OF REVERSING A LIST USING RECURSION
   */
  def reverseList(x: List[Int]): List[Int] = x match {
    case Nil => Nil
    case h :: t => println(reverseList(t)); reverseList(t) ++ List(h)
  }

  /**
   * REVERSING A LIST USING FOLD LEFT!
   */
  def reverseListFold(l: List[Int]) = l.foldLeft(List[Int]())((l, e) => e :: l)

//  println(reverseListFold(r))



  case class Person(name: String, age: Int)

  val l = List(
    Person("Tom", 3), Person("Sally", 30), Person("Tony", 22), Person("Arthur", 1), Person("Gemma", 17)
  )

  /**

  Example of Insertion Sort in Scala.

  def top(l: List[Person]): List[Person] = l match {
    case List()   => List()
    case h :: t => {
      println("bottom("+h+", "+ top(t)+ ")")
      bottom(h, top(t))
    }
  }

  def bottom(p: Person, xs: List[Person]): List[Person] = xs match {
    case List()  => List(p)
    case h :: t => {
      println("head: [" + h + "], tail = [" + t +"]" )
      if (p.age <= h.age){
        println("if("+p.age + " < " + h.age+ ")")
        println(p :: xs)
        p :: xs
      }  else {
        println("Else:" + h :: bottom(p, t))
        h :: bottom(p, t)
      }
    }
  }

  println(top(l))
**/


  /**
   * Example of a MergeSort. Don't understand
   */
  def mergeSort(ls: List[Person]): List[Person] = {
    val split = ls.length / 2
    if(split == 0) ls
    else {
      def merge(l: List[Person], r: List[Person], acc: List[Person] = List()): List[Person] = (l, r) match {
        case (Nil, _) => acc ++ r
        case (_, Nil) => acc ++ l
        case (ls :: ls1, rs :: rs1) =>
          if (ls.age < rs.age) merge(ls1, r, acc :+ ls)
          else merge(l, rs1, acc :+ rs)
      }
      val (l, r) = ls splitAt split
      merge(mergeSort(l), mergeSort(r))
    }
  }

  /**
   * 4
   */
  def four(ls: List[String], mp: Map[String, Int]) = ls.flatMap(mp.get(_))
  val a = List("Tom", "Fred", "Harry")
  val b = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)
  println(a.map(b.get(_)))


}


