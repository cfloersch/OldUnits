package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Period of existence or persistence. The metric system unit for this quantity is "s"
 * (second).
 *
 * @see Frequency
 * @see Speed
 * @see AngularSpeed
 * @see Acceleration
 * @see AngularAcceleration
 * @see ElectricCurrent
 * @see MassFlowRate
 * @see VolumetricFlowRate
 * @see InformationRate
 * @see Power
 */
public interface Time extends Quantity<Time> {

   public static final Unit<Time> UNIT = SI.SECOND;

}
