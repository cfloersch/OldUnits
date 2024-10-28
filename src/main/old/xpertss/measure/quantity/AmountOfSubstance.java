package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the number of elementary entities (molecules, for
 * example) of a substance. The system unit for this quantity is "mol" (mole).
 */
public interface AmountOfSubstance extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<AmountOfSubstance> UNIT = SI.MOLE;

}