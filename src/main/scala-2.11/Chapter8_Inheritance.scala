import javax.management.DescriptorRead

/**
 * Created by barn on 13/11/15.
 */
object Chapter8_Inheritance extends App{

  /**
   * Super Class BankAccount
   */
  class BankAccount(initialBal: Double){
    private var _balance = initialBal
    def currentBalance = _balance
    def deposit(amount: Double) = _balance += amount
    def withdraw(amount: Double) = _balance -= amount
    override def toString = "[Balance: " + currentBalance + "]"
  }

  /**
   * Base class - Inherits all super class methods... primary constructor chains to parents constructor
   */
  class CheckingAccount(value: Double, cost: Double = 1.0) extends BankAccount(value) {
    override def deposit(amount: Double) = super.deposit(amount - cost)
    override def withdraw(amount: Double) = super.withdraw(amount + cost)
  } //Automatically inherits the super classes toString method

  val b = new BankAccount(500)
  println(b)
  b.deposit(10)
  println(b)

  val a = new CheckingAccount(500)
  println(a)
  a.deposit(10)
  a.withdraw(208)
  println(a)

  /**
   * Also extends BankAccount super class. More in depth with interest and
   */
  class SavingsAccount (initialBal: Double, val commission: Double = 1) extends BankAccount(initialBal) {
    val monthlyInterest = 0.10
    private val maxTransactions = 3
    private var currentTransactions = 0

    def isFreeTransactions = currentTransactions <= maxTransactions //Returns bool if current trans < 3 (free transaction)

    override def deposit(amount: Double) = {
      currentTransactions += 1
      if(isFreeTransactions) super.deposit(amount) else super.deposit(amount - commission) //One option but clunky
    }

    override def withdraw(amount: Double) = {
      currentTransactions +=1
      super.withdraw(amount + (if(isFreeTransactions) 0 else commission)) //Second option... more concise!!
    }

    def earnMonthlyInterest() = {
      currentTransactions = 0
      super.deposit(currentBalance * 0.10 / 12)
    }
  }

  /**
   * Testing the bank works. MAYBE WRITE UNIT TESTS TO PRACTISE TESTING
   */
  val bank = new SavingsAccount(500)
  bank.deposit(10)
  println("\n\n\n\n"+bank)
  bank.deposit(10)
  println(bank)
  bank.withdraw(20)
  println(bank)
  bank.deposit(2)
  println(bank)
  bank.earnMonthlyInterest()
  println(bank)
  bank.deposit(10)
  println(bank)

  /**
   * 4 - Abstract class Item - defines method types but does not implement them
   */
  abstract class Item{
    def price: Double
    def description: String
    override def toString = description + " " + price
  }

  /**
   * Sub class of abstract class! Implements the
   */
  class SimpleItem(override val price: Double, override val description: String) extends Item

  class Bundle(override val description: String) extends Item {
    var listOfItems: List[Item] = List() //Creates an empty list of type Item
    def add(item: Item) = {listOfItems = item :: listOfItems}
   override  def price = {listOfItems.map(_.price).sum}
    override def toString = description + "contains: " + listOfItems.map(_.description).mkString(", ") +
      "Price" + listOfItems.map(_.price).mkString(", ")
  }

  /**
   * Create SimpleObjects which together make a Bundle.
   */
  val scissors = new SimpleItem(2.00, "Scissors")
  val plasters = new SimpleItem(1.00, "Plasters")
  val antiseptic = new SimpleItem(3.00, "Antiseptic cream")
  val bandage = new SimpleItem(4.00, "Bandages")

  /**
   * Create the Bundle of items, and add the simple objects into the bundle.
   */
  val first_aid_kit = new Bundle("First Aid Kit ")
  first_aid_kit.add(scissors)
  first_aid_kit.add(plasters)
  first_aid_kit.add(antiseptic)
  first_aid_kit.add(bandage)

  println(first_aid_kit.toString)


  class Point(x: Int = 0, y: Int = 0) {
    override def toString = "["+x + ", " + y + "]"
  }

  class LabeledPoint(val label: String, x: Int, y: Int) extends Point(x, y) {
    override def toString = {"Labeled Point = (%s".format(label) + super.toString + ")"}

  }

  object LabeledPoint {
    def apply(label: String, x: Int, y: Int) = new LabeledPoint(label, x, y)
  }

  val abcd = new Point(3, 3)
  println(abcd)
  val pt = LabeledPoint("Orange Wednesday",44, 44)
  println(pt)






}
