package xpertss.measure.unit;


import xpertss.measure.quantity.Quantity;

/**
 * This class identifies the units created by combining or transforming other units.
 */
public abstract class DerivedUnit<Q extends Quantity> extends Unit<Q> {

   /**
    * Default constructor.
    */
   protected DerivedUnit()
   {
   }
}