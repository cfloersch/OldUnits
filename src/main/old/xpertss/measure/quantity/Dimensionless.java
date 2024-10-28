package xpertss.measure.quantity;

import xpertss.measure.unit.Unit;

/**
 * This interface represents a dimensionless quantity.
 */
public interface Dimensionless extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Dimensionless> UNIT = Unit.ONE;

}