package xpertss.unit.converters;

import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL128;

/**
 * This class represents a converter multiplying numeric values by π (Pi).
 * <p/>
 * All computations are conducted on a value of Pi computed to 101 digits
 * of precision using a {@link java.math.MathContext#DECIMAL128 DECIMAL128}
 * math context.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Pi">Wikipedia: Pi</a>
 */
public final class PiMultiplierConverter extends BaseConverter {

   // PI To 100 decimal digits (precision = 101)
   private static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");

   /**
    * Creates a Pi multiplier converter.
    */
   public PiMultiplierConverter()
   {
   }


   @Override
   public boolean isLinear()
   {
      return true;
   }


   @Override
   public UnitConverter inverse()
   {
      return new PiDivisorConverter();
   }


   @Override
   public BigDecimal convert(BigDecimal value)
   {
      return clean(value.multiply(PI, DECIMAL128));
   }


   @Override
   public boolean equals(Object obj)
   {
      return (obj instanceof PiMultiplierConverter);
   }

   @Override
   public int hashCode()
   {
      return 128;
   }

   @Override
   public final String toString()
   {
      return "(π)";
   }


}
