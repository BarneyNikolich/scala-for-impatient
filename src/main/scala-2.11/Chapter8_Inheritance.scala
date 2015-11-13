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







}
