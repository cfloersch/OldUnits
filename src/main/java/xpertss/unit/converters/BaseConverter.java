package xpertss.unit.converters;

import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BaseConverter extends UnitConverter {


   boolean equals(BigDecimal one, BigDecimal two)
   {
      return one.compareTo(two) == 0;
   }


   /**
    * Anything with a negative scale will be output as an integer so long as
    * it can be done with 7 digits of precision. All trailing decimal zeroes
    * will be striped.
    */
   BigDecimal clean(BigDecimal value)
   {
      if(value.scale() < 0 && value.precision() < 7) {
         int newScale = value.precision() + Math.abs(value.scale());
         if(newScale < 8) {
            return value.setScale(0, RoundingMode.UNNECESSARY);
         }
      }
      while(value.scale() > 0) {
         try {
            value = value.setScale(value.scale() - 1);
         } catch(ArithmeticException e) {
            // If a value other than zero is trimmed an exception is thrown and we are done
            return value;
         }
      }
      return value;
   }



}
