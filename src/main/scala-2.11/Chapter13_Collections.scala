import scala.collection.immutable.{TreeSet, HashMap}

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
    val map = s.zipWithIndex.groupBy(_._1).map(x => (x._1, x._2.map(_._2).toList))
    val newMap = map.toSeq.sortBy(_._1).filter(x => x._2.length <= 2)
    map.toMap
  }



  val test = "hello mate".zipWithIndex.groupBy(x => x._1).map(x => (x._1, x._2.map(x => x._2).toList))

  println(indexes("hello mate"))

//  println(indexes("Cheesy ball bags"))










}
