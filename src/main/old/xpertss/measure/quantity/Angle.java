package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the figure formed by two lines diverging from a
 * common point. The system unit for this quantity is "rad" (radian).
 * This quantity is dimensionless.
 */
public interface Angle extends Dimensionless {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Angle> UNIT = SI.RADIAN;

}