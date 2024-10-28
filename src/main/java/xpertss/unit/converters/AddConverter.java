package xpertss.unit.converters;

import java.math.BigDecimal;
import java.util.Objects;
import org.xpertss.measure.UnitConverter;

import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;

/**
 * This class represents a converter adding a constant offset to numeric values.
 */
public final class AddConverter extends BaseConverter {

   /**
    * Holds the offset.
    */
   private BigDecimal offset;

   /**
    * Creates an additive converter having the specified offset.
    *
    * @param offset the offset value.
    * @throws IllegalArgumentException if offset is <code>0.0</code>
    *                                  (would result in identity converter).
    */
   public AddConverter(BigDecimal offset)
   {
      if(equals(ZERO, Objects.requireNonNull(offset)))
         throw new IllegalArgumentException("Would result in identity converter");
      this.offset = clean(offset);
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
   public boolean isLinear()
   {
      return false;
   }


   @Override
   public UnitConverter inverse()
   {
      return new AddConverter(clean(offset.negate(DECIMAL128)));
   }


   @Override
   public BigDecimal convert(BigDecimal value)
   {
      return clean(value.add(offset, DECIMAL128));
   }

   @Override
   public UnitConverter concatenate(UnitConverter converter)
   {
      if(!(converter instanceof AddConverter)) return super.concatenate(converter);
      BigDecimal newOffset = clean(offset.add(((AddConverter) converter).offset, DECIMAL128));
      return equals(ZERO, newOffset) ? IDENTITY : new AddConverter(newOffset);
   }


   @Override
   public boolean equals(Object obj)
   {
      if(obj instanceof AddConverter) {
         AddConverter that = (AddConverter) obj;
         return Objects.equals(this.offset, that.offset);

      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return offset.hashCode();
   }

   @Override
   public final String toString()
   {
      return "AddConverter(" + offset + ")";
   }

}
