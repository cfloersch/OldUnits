package xpertss.measure.unit;

import xpertss.measure.converter.UnitConverter;
import xpertss.measure.quantity.Quantity;

import java.util.Objects;


/**
 * This class represents the units derived from other units using
 * {@link xpertss.measure.converter.UnitConverter converters}.
 * <p/>
 * <p> Examples of transformed units:[code]
 * CELSIUS = KELVIN.add(273.15);
 * FOOT = METER.multiply(0.3048);
 * MILLISECOND = MILLI(SECOND);
 * [/code]</p>
 * <p/>
 * Transformed units have no label. But like any other units, they
 * may have labels attached to them:[code]
 * UnitFormat.getStandardInstance().label(FOOT, "ft");
 * [/code]
 * or aliases: [code]
 * UnitFormat.getStandardInstance().alias(CENTI(METER)), "centimeter");
 * UnitFormat.getStandardInstance().alias(CENTI(METER)), "centimetre");
 * [/code]</p>
 *
 * @see Unit#add(java.math.BigDecimal)
 * @see Unit#multiply(java.math.BigDecimal)
 * @see Unit#transform(xpertss.measure.converter.UnitConverter)
 * @see UnitFormat
 */
public final class TransformedUnit<Q extends Quantity> extends DerivedUnit<Q> {

   /**
    * Holds the parent unit (not a transformed unit).
    */
   private final Unit<Q> parentUnit;

   /**
    * Holds the converter to the parent unit.
    */
   private final UnitConverter toParentUnit;

   /**
    * Creates a transformed unit from the specified parent unit.
    *
    * @param parentUnit   the untransformed unit from which this unit is
    *                     derived.
    * @param toParentUnit the converter to the parent units.
    * @throws IllegalArgumentException if <code>toParentUnit ==
    *                                  {@link xpertss.measure.converter.UnitConverter#IDENTITY UnitConverter.IDENTITY}</code>
    */
   TransformedUnit(Unit<Q> parentUnit, UnitConverter toParentUnit)
   {
      if(toParentUnit == UnitConverter.IDENTITY) {
         throw new IllegalArgumentException("Identity not allowed");
      }
      this.parentUnit = Objects.requireNonNull(parentUnit);
      this.toParentUnit = Objects.requireNonNull(toParentUnit);
   }

   /**
    * Returns the parent unit for this unit. The parent unit is the
    * untransformed unit from which this unit is derived.
    *
    * @return the untransformed unit from which this unit is derived.
    */
   public Unit<Q> getParentUnit()
   {
      return parentUnit;
   }

   /**
    * Returns the converter to the parent unit.
    *
    * @return the converter to the parent unit.
    */
   public UnitConverter toParentUnit()
   {
      return toParentUnit;
   }

   /**
    * Indicates if this transformed unit is considered equals to the specified
    * object (both are transformed units with equal parent unit and equal
    * converter to parent unit).
    *
    * @param that the object to compare for equality.
    * @return <code>true</code> if <code>this</code> and <code>that</code>
    *         are considered equals; <code>false</code>otherwise.
    */
   public boolean equals(Object that)
   {
      if(this == that) return true;
      if(that instanceof TransformedUnit) {
         TransformedUnit<?> thatUnit = (TransformedUnit<?>) that;
         return this.parentUnit.equals(thatUnit.parentUnit) && this.toParentUnit.equals(thatUnit.toParentUnit);
      }
      return false;
   }

   // Implements abstract method.
   public int hashCode()
   {
      return parentUnit.hashCode() + toParentUnit.hashCode();
   }

   // Implements abstract method.
   public Unit<? super Q> getStandardUnit()
   {
      return parentUnit.getStandardUnit();
   }

   // Implements abstract method.
   public UnitConverter toStandardUnit()
   {
      return parentUnit.toStandardUnit().concat(toParentUnit);
   }

   private static final long serialVersionUID = 1L;

}