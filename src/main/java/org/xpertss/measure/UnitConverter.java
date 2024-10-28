package org.xpertss.measure;

import java.math.BigDecimal;

/**
 * A converter of numeric values between different units.
 * <p/>
 * Instances of this class are obtained through the {@link Unit#getConverterTo(Unit)}
 * method.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Conversion_of_units"> Wikipedia:
 * Conversion of units</a>
 */
public abstract class UnitConverter {

   /**
    * Holds identity converter.
    */
   public static final UnitConverter IDENTITY = new Identity();


   /**
    * Default constructor.
    */
   protected UnitConverter()
   {
   }


   /**
    * Indicates if this converter is an identity converter.
    * The identity converter returns its input argument ({@code convert(x) == x}).
    *
    * @return {@code true} if this converter is an identity converter.
    */
   public boolean isIdentity()
   {
      return false;
   }

   /**
    * Indicates if this converter is linear. A converter is linear if:
    * <p/>
    * <ul>
    * <li>{@code convert(u + v) == convert(u) + convert(v)}</li>
    * <li>{@code convert(r * u) == r * convert(u)}</li>
    * </ul>
    * <p/>
    * For linear converters the following property always hold:
    * <p/>
    * <pre>
    *    y1 = c1.convert(x1);
    *    y2 = c2.convert(x2);
    *    assert y1*y2 == c1.concatenate(c2).convert(x1*x2);
    * </pre>
    *
    * @return {@code true} if this converter is linear; {@code false} otherwise.
    */
   public boolean isLinear() { return true; }

   /**
    * Returns the inverse of this converter. If {@code x} is a valid value,
    * then {@code x == inverse().convert(convert(x))} to within the accuracy
    * of computer arithmetic.
    *
    * TODO Update these javadocs to indicate its accurate to DECIMAL128
    *
    * @return the inverse of this converter.
    */
   public abstract UnitConverter inverse();

   /**
    * Converts a decimal value.
    *
    * @param value the {@code Number} value to convert.
    * @return the {@code Number} value after conversion.
    */
   public abstract BigDecimal convert(BigDecimal value);

   /**
    * Concatenates this converter with another converter. The resulting
    * converter is equivalent to first converting by the specified converter
    * (right converter), and then converting by this converter (left converter).
    *
    * @param converter the other converter to concatenate with this converter.
    * @return the concatenation of this converter with the other converter.
    */
   public UnitConverter concatenate(UnitConverter converter)
   {
      return (converter == IDENTITY) ? this : new Compound(this, converter);
   }





   /**
    * This class represents the identity converter (singleton).
    */
   private static final class Identity extends UnitConverter {

      @Override
      public boolean isIdentity()
      {
         return true;
      }

      @Override
      public Identity inverse()
      {
         return this;
      }


      @Override
      public BigDecimal convert(BigDecimal value)
      {
         return value;
      }

      @Override
      public UnitConverter concatenate(UnitConverter converter)
      {
         return converter;
      }

      @Override
      public boolean equals(Object cvtr)
      {
         return (cvtr instanceof Identity);
      }

      @Override
      public int hashCode()
      {
         return 0;
      }

      @Override
      public boolean isLinear()
      {
         return true;
      }

      public Identity copy()
      {
         return this; // Unique instance.
      }
   }

   /**
    * This class represents converters made up of two or more separate
    * converters (in matrix notation <code>[compound] = [left] x [right]</code>).
    */
   private static final class Compound extends UnitConverter {

      /**
       * Holds the first converter.
       */
      private UnitConverter left;

      /**
       * Holds the second converter.
       */
      private UnitConverter right;

      /**
       * Creates a compound converter resulting from the combined
       * transformation of the specified converters.
       *
       * @param left  the left converter.
       * @param right the right converter.
       */
      public Compound(UnitConverter left, UnitConverter right)
      {
         this.left = left;
         this.right = right;
      }

      @Override
      public boolean isLinear()
      {
         return left.isLinear() && right.isLinear();
      }

      @Override
      public boolean isIdentity()
      {
         return false;
      }


      @Override
      public Compound inverse()
      {
         return new Compound(right.inverse(), left.inverse());
      }


      @Override
      public BigDecimal convert(BigDecimal value)
      {
         return left.convert(right.convert(value));
      }

      @Override
      public boolean equals(Object cvtr)
      {
         if(this == cvtr) return true;
         if(!(cvtr instanceof Compound)) return false;
         Compound that = (Compound) cvtr;
         return (this.left.equals(that.left)) && (this.right.equals(that.right));
      }

      @Override
      public int hashCode()
      {
         return left.hashCode() + right.hashCode();
      }

   }


}
