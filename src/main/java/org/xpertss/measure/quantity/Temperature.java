package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Degree of hotness or coldness of a body or an environment. The metric system unit
 * for this quantity is "K" (Kelvin).
 */
public interface Temperature extends Quantity<Temperature> {

   public static final Unit<Temperature> UNIT = SI.KELVIN;

}
