package xpertss.measure.converter;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;


/**
 * This class represents a converter adding a constant offset to numeric
 * values.
 * <p/>
 * Instances of this class are immutable.
 */
public final class AddConverter extends UnitConverter {

   /**
    * Holds the offset.
    */
   private final BigDecimal offset;

   /**
    * Creates an add converter with the specified offset.
    *
    * @param offset the offset value.
    * @throws IllegalArgumentException if offset is zero (or close to zero).
    */
   public AddConverter(long offset)
   {
      if(offset == 0) throw new IllegalArgumentException("Identity converter not allowed");
      this.offset = new BigDecimal(offset);
   }

   /**
    * Creates an add converter with the specified offset.
    *
    * @param offset the offset value.
    * @throws IllegalArgumentException if offset is zero (or close to zero).
    */
   public AddConverter(BigDecimal offset)
   {
      if(ZERO.equals(offset)) throw new IllegalArgumentException("Identity converter not allowed");
      this.offset = Objects.requireNonNull(offset);
   }



   /**
    * Returns the offset value for this add converter.
    *
    * @return the offset value.
    */
   public BigDecimal getOffset()
   {
      return offset;
   }

   @Override
   public UnitConverter inverse()
   {
      return new AddConverter(offset.negate(DECIMAL128));
   }

   @Override
   public BigDecimal convert(BigDecimal amount)
   {
      return strip(amount.add(offset, DECIMAL128));
   }

   @Override
   public boolean isLinear()
   {
      return false;
   }

   @Override
   public UnitConverter concat(UnitConverter converter)
   {
      if(converter instanceof AddConverter) {
         AddConverter cvtr = (AddConverter) converter;
         if(offset.add(cvtr.offset).equals(ZERO)) return UnitConverter.IDENTITY;
      }
      return super.concat(converter);
   }


   public boolean equals(Object obj)
   {
      if(obj == this) return true;
      if(obj instanceof AddConverter) {
         AddConverter cvtr = (AddConverter) obj;
         return offset.equals(cvtr.offset);
      }
      return false;
   }

   public int hashCode()
   {
      return offset.hashCode();
   }

   private static final long serialVersionUID = 1L;
}