package org.xpertss.measure.quantity;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;
import org.xpertss.measure.units.SI;


/**
 * Dimensionless quantity.
 */
public interface Dimensionless extends Quantity<Dimensionless> {

    public static final Unit<Dimensionless> UNIT = SI.ONE;

}
