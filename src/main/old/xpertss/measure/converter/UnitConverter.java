package xpertss.measure.converter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class represents a converter of numeric values.
 * <p/>
 * It is not required for sub-classes to be immutable (e.g. currency converter).
 * <p/>
 * Sub-classes must ensure ubiquity of the {@link #IDENTITY identity} converter.
 * In other words, if the result of an operation is equivalent to the identity
 * converter, then the unique {@link #IDENTITY} instance should be returned.
 */
public abstract class UnitConverter implements Serializable {

   /**
    * Holds the identity converter (unique). This converter does nothing
    * (<code>ONE.convert(x) == x</code>).
    */
   public static final UnitConverter IDENTITY = new Identity();

   /**
    * Default constructor.
    */
   protected UnitConverter()
   {
   }

   /**
    * Returns the inverse of this converter. If <code>x</code> is a valid
    * value, then <code>x == inverse().convert(convert(x))</code> to within
    * the accuracy of computer arithmetic.
    *
    * @return the inverse of this converter.
    */
   public abstract UnitConverter inverse();

   /**
    * Converts a Decimal.
    *
    * @param x the numeric value to convert.
    * @return the converted numeric value.
    * @throws ConversionException if an error occurs during conversion.
    */
   public abstract BigDecimal convert(BigDecimal x)
      throws ConversionException;

   /**
    * Concatenates this converter with another converter. The resulting
    * converter is equivalent to first converting by the specified converter,
    * and then converting by this converter.
    * <p/>
    * <p>Note: Implementations must ensure that the {@link #IDENTITY} instance
    * is returned if the resulting converter is an identity
    * converter.</p>
    *
    * @param converter the other converter.
    * @return the concatenation of this converter with the other converter.
    */
   public UnitConverter concat(UnitConverter converter)
   {
      return (converter == IDENTITY) ? this : new Compound(converter, this);
   }

   /**
    * Indicates if this converter is linear. A converter is linear if
    * <code>convert(u + v) == convert(u) + convert(v)</code> and
    * <code>convert(r * u) == r * convert(u)</code>.
    * For linear converters the following property always hold:[code]
    * y1 = c1.convert(x1);
    * y2 = c2.convert(x2);
    * then y1*y2 = c1.concat(c2).convert(x1*x2)[/code]
    *
    * @return <code>true</code> if this converter is linear;
    *         <code>false</code> otherwise.
    */
   public abstract boolean isLinear();




   BigDecimal strip(BigDecimal value)
   {
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


   /**
    * This inner class represents the identity converter (singleton).
    */
   private static final class Identity extends UnitConverter {

      @Override
      public UnitConverter inverse()
      {
         return this;
      }

      @Override
      public BigDecimal convert(BigDecimal x)
      {
         return strip(x);
      }

      @Override
      public boolean isLinear()
      {
         return true;
      }

      @Override
      public UnitConverter concat(UnitConverter converter)
      {
         return converter;
      }

      public boolean equals(Object obj)
      {
         return obj == this;
      }

      public int hashCode()
      {
         return System.identityHashCode(this);
      }

      private static final long serialVersionUID = 1L;

   }

   /**
    * This inner class represents a compound converter.
    */
   private static final class Compound extends UnitConverter {

      /**
       * Holds the first converter.
       */
      private final UnitConverter first;

      /**
       * Holds the second converter.
       */
      private final UnitConverter second;

      /**
       * Creates a compound converter resulting from the combined
       * transformation of the specified converters.
       *
       * @param first  the first converter.
       * @param second the second converter.
       */
      private Compound(UnitConverter first, UnitConverter second)
      {
         this.first = Objects.requireNonNull(first);
         this.second = Objects.requireNonNull(second);
      }

      @Override
      public UnitConverter inverse()
      {
         return new Compound(second.inverse(), first.inverse());
      }

      @Override
      public BigDecimal convert(BigDecimal x)
      {
         return second.convert(first.convert(x));
      }

      @Override
      public boolean isLinear()
      {
         return first.isLinear() && second.isLinear();
      }



      public boolean equals(Object obj)
      {
         if(obj == this) return true;
         if(obj instanceof Compound) {
            Compound comp = (Compound) obj;
            return Objects.equals(first, comp.first) &&
                     Objects.equals(second, comp.second);
         }
         return false;
      }

      public int hashCode()
      {
         return Objects.hash(first, second);
      }


      private static final long serialVersionUID = 1L;

   }

}