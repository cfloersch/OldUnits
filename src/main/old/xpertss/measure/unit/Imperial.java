package xpertss.measure.unit;


import xpertss.measure.quantity.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static xpertss.measure.unit.SI.*;
import static xpertss.measure.unit.SIPrefix.*;

/**
 * The system of imperial units or the imperial system (also known as British
 * Imperial) is the system of units first defined in the British Weights and
 * Measures Act of 1824, which was later refined and reduced. The system came
 * into official use across the British Empire. By the late 20th century, most
 * nations of the former empire had officially adopted the metric system as
 * their main system of measurement; however some imperial units are still
 * used in the United Kingdom, Canada and other countries from the British
 * Empire.
 *
 * @see xpertss.measure.unit.SI
 * @see xpertss.measure.unit.NonSI
 * @see <a href="http://en.wikipedia.org/wiki/Imperial_units">Wikipedia:
 *          Imperial Units</a>
 */
public final class Imperial extends SystemOfUnits {

   /**
    * Holds collection of SI units.
    */
   private static HashSet<Unit<?>> UNITS = new HashSet<>();


   private Imperial() { }




   //////////////////
   // LENGTH UNITS //
   //////////////////

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> INCH = intern(CENTI(METER).multiply(2539998).divide(1000000));    // in

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> FOOT = intern(INCH.multiply(12));               // ft

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> ROD = intern(FOOT.multiply(33).divide(2));      // ???

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> CHAIN = intern(ROD.multiply(4));                // ch

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> LINK = intern(CHAIN.divide(100));               //

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> FURLONG = intern(FOOT.multiply(660));           // fur

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> LEAGUE = intern(FOOT.multiply(15840));          // lea

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> FATHOM = intern(FOOT.multiply(6));              // ftm

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> PACE = intern(FOOT.multiply(5).divide(20));

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> YARD = intern(FOOT.multiply(3));                // yd

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> MILE = intern(FOOT.multiply(5280));             // mi

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Length> NAUTICAL_MILE = intern(FOOT.multiply(6080));    // nmi, M, or NM




   /////////////////
   // SPEED UNITS //
   /////////////////

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Speed> KNOT = intern(NAUTICAL_MILE.divide(HOUR).asType(Speed.class));  // kn




   ////////////////
   // AREA UNITS //
   ////////////////

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Area> PERCH = intern(ROD.pow(2).asType(Area.class));                // perch

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Area> ACRE = intern(YARD.pow(2).multiply(1210).asType(Area.class)); // acre

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Area> ROOD = intern(YARD.pow(2).multiply(4840).asType(Area.class)); // rood




   //////////////////
   // VOLUME UNITS //
   //////////////////

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> GALLON = intern(LITER.multiply(454609).divide(100000));  // gal

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> BUSHEL = intern(GALLON.multiply(8));     // bu

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> QUART = intern(GALLON.divide(4));      // qt

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> PINT = intern(QUART.divide(2));        // pt

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> GILL = intern(PINT.divide(4));         // gi

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> FLUID_OUNCE = intern(GILL.divide(5));  // fl oz

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> FLUID_DRAM = intern(FLUID_OUNCE.divide(8));  // fl dr

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Volume> MINIM = intern(FLUID_DRAM.divide(60)); // ♏, Mx, m, m., min





   ////////////////
   // MASS UNITS //
   ////////////////

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> POUND = intern(GRAM.multiply(45359237).divide(100000)); // lb

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> GRAIN = intern(POUND.divide(7000));   // gr

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> DRACHM = intern(POUND.divide(256));   // dr

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> OUNCE = intern(POUND.divide(16));  // oz

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> STONE = intern(POUND.multiply(14));   // st

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> QUARTER = intern(POUND.multiply(28)); //qr or qtr

   /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
   public static final Unit<Mass> TON = intern(POUND.multiply(2240));   // t






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
