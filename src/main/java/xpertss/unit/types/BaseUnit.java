package xpertss.unit.types;

import org.xpertss.measure.Dimension;
import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.UnitConverter;
import java.util.Objects;


/**
 * This class represents the building blocks on top of which all others physical units
 * are created. Base units are always unscaled SI units.
 *
 * @see <a href="http://en.wikipedia.org/wiki/SI_base_unit">Wikipedia: SI base unit</a>
 */
public class BaseUnit<Q extends Quantity<Q>> extends Unit<Q> {


   /**
    * Holds the symbol.
    */
   private final String symbol;

   /**
    * Holds the base unit dimension.
    */
   private final Dimension dimension;

   /**
    * Creates a base unit having the specified symbol and dimension.
    *
    * @param symbol the symbol of this base unit.
    */
   public BaseUnit(String symbol, Dimension dimension)
   {
      this.symbol = Objects.requireNonNull(symbol, "symbol");
      this.dimension = dimension;
   }


   @Override
   public String getSymbol()
   {
      return symbol;
   }



   @Override
   public Unit<Q> getSystemUnit()
   {
      return this;
   }

   @Override
   public UnitConverter toSystemUnit() { return UnitConverter.IDENTITY; }



   @Override
   public Dimension getDimension()
   {
      return dimension;
   }




   @Override
   public final boolean equals(Object obj)
   {
      if(this == obj) return true;
      if(obj instanceof BaseUnit) {
         BaseUnit that = (BaseUnit) obj;
         return Objects.equals(symbol, that.symbol) &&
                  Objects.equals(dimension, that.dimension);
      }
      return false;
   }

   @Override
   public final int hashCode()
   {
      return Objects.hash(symbol, dimension);
   }

   @Override
   public String toString()
   {
      return symbol;
   }

}
