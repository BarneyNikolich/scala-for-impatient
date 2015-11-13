import java.time.Year

import scala.beans.BeanProperty

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
  class BankAccount(private var balance:Double = 0) {
    def deposit(amount: Double) = balance += amount
    def withdraw(amount: Double) = if(balance - amount > 0) balance -= amount
        else throw new Exception("Your balance ("+balance+") too small for withdraw " + amount)
    def getBalance() = balance
  }

  /**
   * 3 - Time class calculates time on a 24 hour clock!! - USEFUL METHOD FOR CALCULATING TIME
   */
  class Time(private var h: Int = 0, private var m: Int = 0) {
    private val mins = h * 60 + m

    def hours = mins / 60 // Divide by 60 gives you the hour
    def minutes = mins % 60 //Modulo 60 gives the remainder - the minutes in the hour
    def before(other: Time):Boolean = {
      if(mins < other.mins) true else false
    }
    override def toString = hours + ":" + minutes
  }

  val t = new Time(23, 30)
  val o = new Time(23, 20)
  println(t.before(o))
  println(o)

  /**
   *  @ BeanProperty Generates Java Get and set method for the variables!! Useful
   */
  class Student(@BeanProperty var name: String, @BeanProperty var id: Long)
  val barn = new Student("Barney", 2229)

  /**
   * 6 - Primary constructor which turns age to 0 if it is negative.
   */
  class Personn (var age: Int) {
    if (age < 0 ) age = 0
  }

  /**
   * 7 - Person with primary constructor and toeknising
   */
  class Person(val name: String){
    var firstname = name.split("\\s+").apply(0)
    var secondname = name.split("\\s+").apply(1)

    println(firstname +" "+ secondname)
  }
  val barney = new Person("Barney       Nikolich")


  /**
   * 8 - Car class
   */
  class Car(manufacturer: String, model: String, modelYear: Int = -1, var licensePlate: String = "")
  val a = new Car("Toyota", "Yaris", 1994)
  val b = new Car("Peugeot", "206")
//Example of how to only pass a few values in the parameters. Primary constructor saves code!!

  /**
   *  Primary and secondary constructor classes
   */
    class Employee(defName: String, defSalary: Double) {
      val name: String = defName
      val salary: Double = defSalary
  }


  class AuxilaryConst(val name: String, var salary: Double){
    def this() = this("Barney", 300.00)

  }



}

