/**
 * Created by barn on 11/11/15.
 */
object Chapter5_Classes extends App {

  /**
   *  1 - Stop Int.MaxInt going negative. Class with private members - how to access it
   */
  class Counter(private var value: Int = 0) {
    def increment() = if(value < Int.MaxValue) value += 1 //Int.MaxValue is the highest possible int. Above that goes to negative
    def current() = value //How to access the private member variable.
  }

  val c = new Counter(Int.MaxValue)
  println(c.current)
  println(c.increment())
  println(c.current())

  /**
   * 2 - BankAccount class
   */
  class BankAccount 


}

