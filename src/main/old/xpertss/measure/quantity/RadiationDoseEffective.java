package xpertss.measure.quantity;


import xpertss.measure.unit.SI;
import xpertss.measure.unit.Unit;

/**
 * This interface represents the effective (or "equivalent") dose of radiation
 * received by a human or some other living organism. The system unit for
 * this quantity is "Sv" (Sievert).
 */
public interface RadiationDoseEffective extends Quantity {

   /**
    * Holds the SI unit (Système International d'Unités) for this quantity.
    */
   public final static Unit<RadiationDoseEffective> UNIT = SI.SIEVERT;

}