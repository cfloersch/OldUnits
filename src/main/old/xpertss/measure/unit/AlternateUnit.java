package xpertss.measure.unit;


import xpertss.measure.converter.UnitConverter;
import xpertss.measure.quantity.Quantity;

import java.util.Objects;

/**
 * <p> This class represents the units used in expressions to distinguish
 * between quantities of a different nature but of the same dimensions.</p>
 * <p/>
 * <p> Instances of this class are created through the
 * {@link xpertss.measure.unit.Unit#alternate(String)} method.</p>
 */
public final class AlternateUnit<Q extends Quantity> extends DerivedUnit<Q> {

   /**
    * Holds the symbol.
    */
   private final String symbol;

   /**
    * Holds the parent unit (a system unit).
    */
   private final Unit<?> parent;

   /**
    * Creates an alternate unit for the specified unit identified by the
    * specified symbol.
    *
    * @param symbol the symbol for this alternate unit.
    * @param parent the system unit from which this alternate unit is
    *               derived.
    * @throws UnsupportedOperationException if the source is not
    *                                       a standard unit.
    * @throws IllegalArgumentException      if the specified symbol is
    *                                       associated to a different unit.
    */
   AlternateUnit(String symbol, Unit<?> parent)
   {
      if(!parent.isStandardUnit()) {
         throw new UnsupportedOperationException(this + " is not a standard unit");
      }
      this.symbol = Objects.requireNonNull(symbol);
      this.parent = Objects.requireNonNull(parent);
      // Checks if the symbol is associated to a different unit.
      /*
      synchronized(Unit.SYMBOL_TO_UNIT) {
         Unit<?> unit = Unit.SYMBOL_TO_UNIT.get(symbol);
         if(unit == null) {
            Unit.SYMBOL_TO_UNIT.put(symbol, this);
            return;
         }
         if(unit instanceof AlternateUnit) {
            AlternateUnit<?> existingUnit = (AlternateUnit<?>) unit;
            if(symbol.equals(existingUnit.symbol) && parent.equals(existingUnit.parent)) {
               return; // OK, same unit.
            }
         }
         throw new IllegalArgumentException("Symbol " + symbol + " is associated to a different unit");
      }
      */
   }

   /**
    * Returns the symbol for this alternate unit.
    *
    * @return this alternate unit symbol.
    */
   public final String getSymbol()
   {
      return symbol;
   }

   /**
    * Returns the parent unit from which this alternate unit is derived
    * (a system unit itself).
    *
    * @return the parent of the alternate unit.
    */
   @SuppressWarnings("unchecked")
   public final Unit<? super Q> getParent()
   {
      return (Unit<? super Q>) parent;
   }

   @Override
   public final Unit<? super Q> getStandardUnit()
   {
      return this;
   }

   @Override
   public final UnitConverter toStandardUnit()
   {
      return UnitConverter.IDENTITY;
   }

   /**
    * Indicates if this alternate unit is considered equals to the specified
    * object (both are alternate units with equal symbol, equal base units
    * and equal converter to base units).
    *
    * @param that the object to compare for equality.
    * @return <code>true</code> if <code>this</code> and <code>that</code>
    *         are considered equals; <code>false</code>otherwise.
    */
   public boolean equals(Object that)
   {
      if(this == that) return true;
      if(that instanceof AlternateUnit) {
         AlternateUnit<?> thatUnit = (AlternateUnit<?>) that;
         return this.symbol.equals(thatUnit.symbol); // Symbols are unique.
      }
      return false;
   }

   // Implements abstract method.
   public int hashCode()
   {
      return symbol.hashCode();
   }

   private static final long serialVersionUID = 1L;
}