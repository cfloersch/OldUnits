package xpertss.unit.types;


import org.xpertss.measure.Dimension;
import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.UnitConverter;

/**
 * This class represents the units derived from other units using
 * {@link UnitConverter converters}.
 * <p/>
 * Examples of transformed units:
 * <p/>
 * <pre>
 *   CELSIUS = KELVIN.offset(273.15);
 *   FOOT = METRE.multiply(3048).divide(10000);
 *   MILLISECOND = MILLI(SECOND);
 * </pre>
 * <p/>
 * Instances of this class are created through the {@link Unit#transform} method.
 *
 * @param <Q> The type of the quantity measured by this unit.
 */
public final class TransformedUnit<Q extends Quantity<Q>> extends Unit<Q> {

   /**
    * Holds the parent unit (always a system unit).
    */
   private final Unit<Q> parentUnit;

   /**
    * Holds the converter to the parent unit.
    */
   private final UnitConverter toParentUnit;

   /**
    * Creates a transformed unit from the specified system unit.
    *
    * @param parentUnit   the system unit from which this unit is derived.
    * @param toParentUnit the converter to the parent units.
    * @throws IllegalArgumentException if the specified parent unit is not a
    *                                  system unit
    */
   public TransformedUnit(Unit<Q> parentUnit, UnitConverter toParentUnit)
   {
      if(!parentUnit.isSystemUnit())
         throw new IllegalArgumentException("The parent unit: " + parentUnit + " is not a system unit");
      this.parentUnit = parentUnit;
      this.toParentUnit = toParentUnit;
   }


   @Override
   public UnitConverter toSystemUnit()
   {
      return parentUnit.toSystemUnit().concatenate(toParentUnit);
   }

   @Override
   public Unit<? super Q> getSystemUnit()
   {
      return parentUnit.getSystemUnit();
   }

   @Override
   public Dimension getDimension()
   {
      return parentUnit.getDimension();
   }


   @Override
   public boolean equals(Object that)
   {
      if(this == that) return true;
      if(!(that instanceof TransformedUnit)) return false;
      TransformedUnit thatUnit = (TransformedUnit) that;
      return this.parentUnit.equals(thatUnit.parentUnit) && this.toParentUnit.equals(thatUnit.toParentUnit);
   }


   @Override
   public int hashCode()
   {
      return parentUnit.hashCode() + toParentUnit.hashCode();
   }
}
