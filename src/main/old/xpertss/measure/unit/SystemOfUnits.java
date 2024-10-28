package xpertss.measure.unit;

import java.util.Set;

/**
 * This class represents a system of units, it groups units together for
 * historical or cultural reasons. Nothing prevents a unit from belonging
 * to several system of units at the same time (for example an imperial
 * system would have many of the units held by {@link NonSI}).
 */
public abstract class SystemOfUnits {

   /**
    * Returns a read only view over the units defined in this system.
    *
    * @return the collection of units.
    */
   public abstract Set<Unit<?>> getUnits();

}
