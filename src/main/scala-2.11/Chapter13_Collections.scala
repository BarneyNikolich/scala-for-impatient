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

  def removeZeros(x: List[Int]): List[Int] = x match {
    case Nil => Nil
    case h :: t => if(h != 0) h :: removeZeros(t) else removeZeros(t)
  }

  def filterZero(x: List[Int]) = x.foldLeft(1)((x:Int, y: Int) => if(x == 0) y else x )

//  println(removeZeros(listy))


  val r = List(1 , 2, 3, 4, 5, 6, 7, 8)

  def reverseList(x: List[Int]): List[Int] = {
    x match {
      case Nil => Nil
      case h :: t => reverseList(t) ++ List(h)
    }
  }

//  val reversed = r.fold(List[Int])((h, t)=> t :: h)

  def reverseListFold(l: List[Int]) = l.foldLeft(List[Int]())((l, e) =>  {
    println("l: " + l)
    println("e: " + e)
    println(e :: l)
    println()
    e :: l
  })

//  println(reverseListFold(r))



  case class Person(name: String, age: Int)

  val l = List(
    Person("Tom", 3), Person("Sally", 30), Person("Tony", 22), Person("Arthur", 1), Person("Gemma", 17)
  )

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


}


