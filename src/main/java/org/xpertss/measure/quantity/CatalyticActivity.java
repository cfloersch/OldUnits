package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Catalytic activity. The metric system unit for this quantity is "kat" (katal).
 */
public interface CatalyticActivity extends Quantity<CatalyticActivity> {

   public static final Unit<CatalyticActivity> UNIT = SI.KATAL;

}
