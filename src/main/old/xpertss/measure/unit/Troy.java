package xpertss.measure.unit;

import xpertss.measure.quantity.Mass;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static xpertss.measure.unit.SI.*;

/**
 * Troy weight is a system of units of mass customarily used for precious metals and gemstones.
 */
public final class Troy extends SystemOfUnits {

   /**
    * Holds collection of SI units.
    */
   private static HashSet<Unit<?>> UNITS = new HashSet<>();


   private Troy() { }



   //////////
   // Mass //
   //////////

   /**
    * A unit of mass equal to <code>31.1034768 g</code> (standard name <code>oz t</code>).
    */
   public static final Unit<Mass> OUNCE = intern(GRAM.multiply(311034768).divide(10000000));

   /**
    * A unit of mass equal to 12 troy ounces (standard name <code>lb t</code>).
    */
   public static final Unit<Mass> POUND = intern(OUNCE.multiply(12));

   /**
    * A unit of mass equal to 12 troy ounces (standard name <code>dwt</code>).
    */
   public static final Unit<Mass> PENNYWEIGHT = intern(OUNCE.divide(20));










   /////////////////////
   // Collection View //
   /////////////////////

   /**
    * Returns a read only view over theunits defined in this class.
    *
    * @return the collection of SI units.
    */
   public Set<Unit<?>> getUnits()
   {
      return Collections.unmodifiableSet(UNITS);
   }

   /**
    * Adds a new unit to the collection.
    *
    * @param unit the unit being added.
    * @return <code>unit</code>.
    */
   private static <U extends Unit<?>> U intern(U unit)
   {
      UNITS.add(unit);
      return unit;
   }


}
