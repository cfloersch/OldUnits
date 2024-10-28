package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Radioactive activity. The metric system unit for this quantity is "Bq"
 * (Becquerel).
 */
public interface Radioactivity extends Quantity<Radioactivity> {

   public static final Unit<Radioactivity> UNIT = SI.BECQUEREL;

}
