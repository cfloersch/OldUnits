package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the angle formed by three or more planes intersecting
 * at a common point. The system unit for this quantity is "sr" (steradian).
 * This quantity is dimensionless.
 */
public interface SolidAngle extends Dimensionless {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<SolidAngle> UNIT = SI.STERADIAN;

}