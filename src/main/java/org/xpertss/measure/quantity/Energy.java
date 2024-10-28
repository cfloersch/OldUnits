package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Capacity of a physical system to do work. The metric system unit for this quantity
 * "J" (Joule).
 *
 * @see Force
 * @see Power
 * @see Time
 */
public interface Energy extends Quantity<Energy> {

   public static final Unit<Energy> UNIT = SI.JOULE;

}
