package xpertss.measure;

import xpertss.measure.converter.UnitConverter;
import xpertss.measure.quantity.Quantity;
import xpertss.measure.unit.Unit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p> This class represents a measure whose value is an arbitrary-precision
 * decimal number.</p>
 */
class DecimalMeasure<Q extends Quantity> implements Measurable<Q>, Serializable {

   /**
    * Holds the BigDecimal value.
    */
   private final BigDecimal value;

   /**
    * Holds the unit.
    */
   private final Unit<Q> unit;


   /**
    * Creates a decimal measure for the specified number stated in the
    * specified unit.
    *
    * @param value The value or magnitude of this measure
    * @param unit The unit used with this measure
    */
   protected DecimalMeasure(BigDecimal value, Unit<Q> unit)
   {
      this.value = UnitConverter.IDENTITY.convert(value);
      this.unit = Objects.requireNonNull(unit);
   }


   public Unit<Q> getUnit()
   {
      return unit;
   }

   public BigDecimal getValue()
   {
      return value;
   }

   public BigDecimal getValue(Unit<Q> unit)
   {
      if((unit == null) || (unit == this.unit) || (unit.equals(this.unit))) return value;
      return this.unit.getConverterTo(unit).convert(getValue());
   }



   /**
    * Returns the decimal measure equivalent to this measure but stated in the
    * specified unit.
    *
    * @param unit    the new measurement unit.
    * @return the measure stated in the specified unit.
    */
   public DecimalMeasure<Q> getAs(Unit<Q> unit)
   {
      if((unit == this.unit) || (unit.equals(this.unit))) return this;
      UnitConverter cvtr = this.unit.getConverterTo(unit);
      return new DecimalMeasure<>(cvtr.convert(value), unit);
   }


   private static final long serialVersionUID = 1L;

   /**
    * Compares this measure against the specified object for strict equality
    * (same unit and amount). To compare measures stated using different units
    * the {@link #compareTo} method should be used.
    *
    * @param  obj the object to compare with.
    * @return <code>true</code> if both objects are identical (same
    *         unit and same amount); <code>false</code> otherwise.
    */
   public boolean equals(Object obj)
   {
      if (obj instanceof Measurable) {
         Measurable that = (Measurable) obj;
         return Objects.equals(unit, that.getUnit()) &&
                  Objects.equals(value, that.getValue());
      }
      return false;
   }

   /**
    * Returns the hash code for this scalar.
    *
    * @return the hash code value.
    */
   public int hashCode()
   {
      return Objects.hash(value, unit);
   }

   /**
    * Returns the <code>String</code> representation of this measure
    * The string produced for a given measure is always the same;
    * it is not affected by locale.  This means that it can be used
    * as a canonical string representation for exchanging data,
    * or as a key for a Hashtable, etc.  Locale-sensitive
    * measure formatting and parsing is handled by the {@link
    * MeasureFormat} class and its subclasses.
    *
    * @return the string representation of this measure.
    */
   public String toString()
   {
      return MeasureFormat.getInstance().format(this);
   }




   /**
    * Compare two measurables of the same quantity type.
    */
   public int compareTo(Measurable<Q> that)
   {
      BigDecimal value = getValue(getUnit());
      BigDecimal other = that.getValue(getUnit());
      return value.compareTo(other);
   }
}
