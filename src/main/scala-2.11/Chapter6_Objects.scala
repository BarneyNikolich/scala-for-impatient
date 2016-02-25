/**
 * Created by Barn on 12/11/15.
 */
object Chapter6_Objects extends App {

  object Conversions {
    def inchesToCentimeters(inches: Double) = inches * 2.54
    def gallonsToLitres(gallons: Double) = gallons * 4.54609
    def milesToKilometres(miles: Double) = miles * 1.60934
  }

  class UnitConversion(convertVal: Double) {
      def convert(value: Double) = convertVal * value
  }

  object InchesToCentimeters extends UnitConversion(2.54)
  object gallonsToLitres extends UnitConversion(4.54609)
  object milesToKilometres extends UnitConversion(1.60934)

  printf("%2.1f miles = %2.1f Km", 12.0, milesToKilometres.convert(33))

//  println("%f inches = %f centimetres".format(12.0, InchesToCentimeters.convert(4.0)))


}
