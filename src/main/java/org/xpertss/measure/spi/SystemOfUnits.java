package org.xpertss.measure.spi;

import java.util.Set;

import org.xpertss.measure.Dimension;
import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;

/**
 * TODO Do we need to have this class? Maybe make it an enum that allows us
 * to access a system of units (SI, US_Customary, Imperial, etc).
 * <p/>
 * A system of units grouped together for historical or cultural reasons.
 * <p/>
 * Common system of units are "SI" (System International), "Imperial" (British),
 * "US" (US Customary).
 * <p/>
 * Nothing prevents a unit from belonging to several systems of units at the
 * same time (for example an {@code Imperial} system would have many of the
 * units held by the {@code US} Customary system).
 *
 * @see <a href="http://en.wikipedia.org/wiki/International_System_of_Units">
 * Wikipedia: International System of Units</a>
 * @see <a href="http://en.wikipedia.org/wiki/Systems_of_measurement">
 * Wikipedia: System of measurement</a>
 */
public interface SystemOfUnits {

   /**
    * @return a name
    */
   String getName();

   /**
    * Returns the default unit for the specified quantity.
    *
    * @param <Q>          the compile-time quantity type.
    * @param quantityType the quantity type.
    * @return the unit for the specified quantity.
    */
   <Q extends Quantity<Q>> Unit<Q> getUnit(Class<Q> quantityType);

   /**
    * Returns a read only view over the units defined in this system.
    *
    * @return the collection of units.
    */
   Set<? extends Unit<?>> getUnits();

   /**
    * Returns the units defined in this system having the specified dimension
    * (convenience method).
    *
    * @param dimension the dimension of the units to be returned.
    * @return the collection of units of specified dimension.
    */
   Set<? extends Unit<?>> getUnits(Dimension dimension);


   // TODO???
   //Unit<?> getUnit(String unit);
}
