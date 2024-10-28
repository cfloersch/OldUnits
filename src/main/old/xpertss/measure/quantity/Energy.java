package xpertss.measure.quantity;

import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the capacity of a physical system to do work.
 * The system unit for this quantity "J" (Joule).
 */
public interface Energy extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Energy> UNIT = SI.JOULE;

}