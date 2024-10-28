package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Amount of space occupied by a three-dimensional object or region of space.
 * The metric system unit for this quantity is "m³" (cubic metre).
 *
 * @see Length
 * @see Area
 * @see VolumetricDensity
 * @see VolumetricFlowRate
 */
public interface Volume extends Quantity<Volume> {

   public static final Unit<Volume> UNIT = SI.CUBIC_METRE;

}
