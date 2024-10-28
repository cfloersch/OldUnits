package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This class represents the degree of hotness or coldness of a body or
 * an environment. The system unit for this quantity is "K" (Kelvin).
 */
public interface Temperature extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<Temperature> UNIT = SI.KELVIN;

}