

/**
 * Created by barn on 13/11/15.
 */
object Chapter6_Objects_Enums extends App {

  /**
   *  2 - Create a general super class and objects which extend it!
   */
  class UnitConversion(val factor: Double) {
    def convert(value: Double) = factor * value
  }

  object InchesToCentimeters extends UnitConversion(2.54)
  object GallonsToLitres extends UnitConversion(4.54609)
  object MilesToKilometres extends UnitConversion(1.60934)

  printf("%G Miles = %G Km", 31.00, MilesToKilometres.convert(31.07))

  /**
   * 3 - Object extending Points.
   */
  object Origin extends java.awt.Point
  println("\n"+Origin.toString)

  /**
   * 4 - Class with a Companion object! Allows you to create objects with the Apply method.
   */
  class Point(x: Int, y: Int) {
    override def toString = "["+x+", "+y+"]"
  }

  object Point {
    def apply(x: Int, y: Int) = new Point(x, y)
  }
  val p = Point(2,4)
  println(p)

  /**
   *  Mimic the main method in JAVA.
   */
  object reverse extends App {
    for(args <- args.reverse)println(args) //For loop reversing the arguments that are passed into the trait.
  }
  reverse.main(Array("Hello", "World")) //Acts as the arguments to the main method.

  object Suits extends Enumeration {
    type Suits = Value
    val spades = Value("♠")
    val hearts = Value("♥")
    val clubs = Value("♣")
    val diamonds = Value("♦")
  }

  /**
   * Method checks if suit is ENUM red. Returns true if so OR False
   */
  import Suits._
  def isRed(suit: Suits) = suit == Suits.diamonds || suit == Suits.hearts
  println(for(s <- Suits.values) yield (s, isRed(s)))


  /**
   * Create ENUMs of Colours - With and ID followed by the name!
   */
  object RGB extends Enumeration {
    val green = Value(0x00ff00, "Green")
    val yellow = Value(0xffff00, "Yellow")
    val cyan = Value(0x00ffff, "Cyan")
    val white = Value(0xffffff, "White")
    val black = Value(0x000000, "Black")
    val blue = Value(0x0000ff, "Blue")
    val magenta = Value(0xff00ff, "Magenta")
    val red = (0xff0000, "Red")
  }

  for(i <- RGB.values) println("0x%06x: %s".format(i.id, i)) //Print format allows n number of 0s.

}