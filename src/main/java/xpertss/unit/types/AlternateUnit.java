package xpertss.unit.types;


import org.xpertss.measure.Dimension;
import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.UnitConverter;
import java.util.Objects;

/**
 * This class represents units used in expressions to distinguish between
 * quantities of a different nature but of the same dimensions.
 */
public final class AlternateUnit<Q extends Quantity<Q>> extends Unit<Q> {


   /**
    * Holds the parent unit (a system unit).
    */
   private final Unit<?> parentUnit;

   /**
    * Holds the symbol for this unit.
    */
   private final String symbol;


   /**
    * Creates an alternate unit for the specified system unit identified by the
    * specified name and symbol.
    */
   public AlternateUnit(Unit<?> parentUnit, String symbol)
   {
      if(!Objects.requireNonNull(parentUnit, "parentUnit").isSystemUnit())
         throw new IllegalArgumentException("The parent unit: " + parentUnit + " is not an unscaled SI unit");
      this.parentUnit = (parentUnit instanceof AlternateUnit) ? ((AlternateUnit) parentUnit).getParentUnit() : parentUnit;
      this.symbol = Objects.requireNonNull(symbol, "symbol");
   }


   /**
    * Returns the parent unit of this alternate unit, always a system unit and
    * never an alternate unit.
    *
    * @return the parent unit.
    */
   public Unit<?> getParentUnit()
   {
      return parentUnit;
   }


   @Override
   public String getSymbol()
   {
      return symbol;
   }

   @Override
   public Dimension getDimension()
   {
      return parentUnit.getDimension();
   }


   @Override
   public UnitConverter toSystemUnit()
   {
      return parentUnit.toSystemUnit();
   }

   @Override
   public Unit<Q> getSystemUnit()
   {
      return this; // Alternate units are SI units.
   }





   @Override
   public boolean equals(Object obj)
   {
      if(this == obj) return true;
      if(obj instanceof AlternateUnit) {
         AlternateUnit that = (AlternateUnit) obj;
         return Objects.equals(parentUnit, that.parentUnit) &&
                  Objects.equals(symbol, that.symbol);

      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(symbol, parentUnit);
   }

   @Override
   public String toString()
   {
      return symbol;
   }

}
