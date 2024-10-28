package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents a measure of data amount.
 * The system unit for this quantity is "bit". This quantity is dimensionless.
 */
public interface Data extends Dimensionless {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Data> UNIT = SI.BIT;

}