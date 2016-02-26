object Chapter12_Higher_order_functions extends App {


  /**
   * 1
   */
  def values(fun: (Int) => Int, low: Int, high: Int) = (low to high).map((x => (x, fun(x))))
  println(values((x => x * x), -5, 5))

  /**
   * 2
   */
  val list = List(1, 4, 55, 4, 2, 50, 54, 3, 33)
  val largest = list.reduceLeft((x, y) => if(x > y) x else y)
  println(largest)

  /**
   * 3 - Implement Factorial with ReduceLeft
   */
  def factorialReduceLeft(n: Int) =
    (0 to n).reduceLeft((x, y) => if(x == 0) 1 else x * y)
  def factorialReduceLeft1(n: Int) =
    (1 to n).reduceLeft(_ * _)

  /**
   * 4 - Factorial with foldLeft... avoids the 0 case
   */
  def factorialFoldLeft(n: Int) = (0 to n).foldLeft(1)(_ * _)

  /**
   * 5
   */
  def largest(fun: (Int) => Int, inputs: Seq[Int]) = inputs.map(fun).max
  println(largest(x => 10 * x - x * x, 1 to 10))

  /**
   * 6
   */
  def largestResultInput(fun: (Int) => Int, inputs: Seq[Int]) = inputs.map(x => (x, fun(x))).reduceLeft((x, y) => if(x._2 > y._2) x else y)._1
  println(largestResultInput(x => 10 * x - x * x, 1 to 10))

  /**
   * 7
   */
  def adjustToPair(fun: (Int, Int => Int))(a: Int, b: Int) = fun(a, b)


}
