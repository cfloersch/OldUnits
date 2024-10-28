package xpertss.measure.unit;

import xpertss.measure.quantity.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static xpertss.measure.unit.SI.*;
import static xpertss.measure.unit.SIPrefix.*;

/**
 * This class contains units that are not part of the International System of Units,
 * that is, they are outside the SI, but are important and widely used. In many cases
 * these units are known both within the US System and the British Imperial System.
 * This class contains the US variant in all of these cases.
 *
 * @see xpertss.measure.unit.SI
 * @see Imperial
 */
public final class NonSI extends SystemOfUnits {

   /**
    * Holds collection of NonSI units.
    */
   private static HashSet<Unit<?>> UNITS = new HashSet<>();

   /**
    * Holds the standard gravity constant: 9.80665 m/s² exact.
    */
   private static final int STANDARD_GRAVITY_DIVIDEND = 980665;
   private static final int STANDARD_GRAVITY_DIVISOR = 100000;

   /**
    * Holds the international foot: 0.3048 m exact.
    */
   private static final int INTERNATIONAL_FOOT_DIVIDEND = 3048;
   private static final int INTERNATIONAL_FOOT_DIViSOR = 10000;

   /**
    * Holds the avoirdupois pound: 0.45359237 kg exact
    */
   private static final int AVOIRDUPOIS_POUND_DIVIDEND = 45359237;
   private static final int AVOIRDUPOIS_POUND_DIVISOR = 100000000;

   /**
    * Holds the Avogadro constant.
    */
   private static final BigDecimal AVOGADRO_CONSTANT = new BigDecimal(6.02214199e23); // (1/mol).

   /**
    * Holds the electric charge of one electron.
    */
   private static final BigDecimal ELEMENTARY_CHARGE = new BigDecimal(1.602176462e-19); // (C).

   private static final BigDecimal PI = new BigDecimal("3.14159265358979323846264338327950288419716939937510");

   /**
    * Default constructor (prevents this class from being instantiated).
    */
   private NonSI()
   {
   }





   ///////////////////
   // Dimensionless //
   ///////////////////

   /**
    * A dimensionless unit equals to <code>0.01</code>
    * (standard name <code>%</code>).
    */
   public static final Unit<Dimensionless> PERCENT = intern(Unit.ONE.divide(100));

   /**
    * A logarithmic unit used to describe a ratio
    * (standard name <code>dB</code>).
    */
   /*
   TODO Uncomment when LogConverter is functional
   public static final Unit<Dimensionless> DECIBEL = intern(Unit.ONE.transform(new LogConverter(BigDecimal.TEN).inverse().concat(new RationalConverter(1, 10))));
   */





   /////////////////////////
   // Amount of substance //
   /////////////////////////

   /**
    * A unit of amount of substance equals to one atom
    * (standard name <code>atom</code>).
    */
   public static final Unit<AmountOfSubstance> ATOM = intern(MOLE.divide(AVOGADRO_CONSTANT));





   ////////////
   // Length //
   ////////////

   /**
    * A unit of length equal to <code>0.3048 m</code>
    * (standard name <code>ft</code>).
    */
   public static final Unit<Length> FOOT = intern(METRE.multiply(INTERNATIONAL_FOOT_DIVIDEND).divide(INTERNATIONAL_FOOT_DIViSOR));

   /**
    * A unit of length equal to <code>1200/3937 m</code>
    * (standard name <code>foot_survey</code>).
    * See also: <a href="http://www.sizes.com/units/foot.htm">foot</a>
    */
   public static final Unit<Length> SURVEY_FOOT = intern(METRE.multiply(1200).divide(3937));

   /**
    * A unit of length equal to <code>0.9144 m</code>
    * (standard name <code>yd</code>).
    */
   public static final Unit<Length> YARD = intern(METRE.multiply(9144).divide(10000));

   /**
    * A unit of length equal to <code>0.0254 m</code>
    * (standard name <code>in</code>).
    */
   public static final Unit<Length> INCH = intern(METRE.multiply(254).divide(10000));

   /**
    * A unit of length equal to <code>1609.344 m</code>
    * (standard name <code>mi</code>).
    */
   public static final Unit<Length> MILE = intern(METRE.multiply(1609344).divide(1000));

   /**
    * A unit of length equal to <code>1852.0 m</code>
    * (standard name <code>nmi</code>).
    */
   public static final Unit<Length> NAUTICAL_MILE = intern(METRE.multiply(1852));

   /**
    * A unit of length equal to <code>1E-10 m</code>
    * (standard name <code>Å</code>).
    */
   public static final Unit<Length> ANGSTROM = intern(METRE.divide(10000000000L));

   /**
    * A unit of length equal to the average distance from the center of the
    * Earth to the center of the Sun (standard name <code>ua</code>).
    */
   public static final Unit<Length> ASTRONOMICAL_UNIT = intern(METRE.multiply(149597870691L));

   /**
    * A unit of length equal to the distance that light travels in one year
    * through a vacuum (standard name <code>ly</code>).
    */
   public static final Unit<Length> LIGHT_YEAR = intern(METRE.multiply(new BigDecimal(9.460528405e15)));

   /**
    * A unit of length equal to the distance at which a star would appear to
    * shift its position by one arcsecond over the course the time
    * (about 3 months) in which the Earth moves a distance of
    * {@link #ASTRONOMICAL_UNIT} in the direction perpendicular to the
    * direction to the star (standard name <code>pc</code>).
    */
   public static final Unit<Length> PARSEC = intern(METRE.multiply(new BigDecimal(30856770e9)));






   //////////
   // Time //
   //////////


   /**
    * A unit of duration equal to <code>24 {@link SI#HOUR}</code>
    * (standard name <code>d</code>).
    */
   public static final Unit<Time> DAY = intern(HOUR.multiply(24));

   /**
    * A unit of duration equal to <code>7 {@link #DAY}</code>
    * (standard name <code>week</code>).
    */
   public static final Unit<Time> WEEK = intern(DAY.multiply(7));

   /**
    * A unit of duration equal to 365 days, 5 hours, 49 minutes,
    * and 12 seconds (standard name <code>year</code>).
    */
   public static final Unit<Time> YEAR = intern(SECOND.multiply(31556952));

   /**
    * A unit of duration equal to one twelfth of a year
    * (standard name <code>month</code>).
    */
   public static final Unit<Time> MONTH = intern(SECOND.multiply(2629746));

   /**
    * A unit of duration equal to the time required for a complete rotation of
    * the earth in reference to any star or to the vernal equinox at the
    * meridian, equal to 23 hours, 56 minutes, 4.09 seconds
    * (standard name <code>day_sidereal</code>).
    */
   public static final Unit<Time> DAY_SIDEREAL = intern(SECOND.multiply(new BigDecimal(86164.09)));

   /**
    * A unit of duration equal to one complete revolution of the
    * earth about the sun, relative to the fixed stars, or 365 days, 6 hours,
    * 9 minutes, 9.54 seconds (standard name <code>year_sidereal</code>).
    */
   public static final Unit<Time> YEAR_SIDEREAL = intern(SECOND.multiply(new BigDecimal(31558149.54)));

   /**
    * A unit of duration equal to <code>365 {@link #DAY}</code>
    * (standard name <code>year_calendar</code>).
    */
   public static final Unit<Time> YEAR_CALENDAR = intern(DAY.multiply(365));





   //////////
   // Mass //
   //////////

   /**
    * A unit of mass equal to 1/12 the mass of the carbon-12 atom
    * (standard name <code>u</code>).
    */
   public static final Unit<Mass> ATOMIC_MASS = intern(KILOGRAM.multiply(new BigDecimal(1e-3)).divide(AVOGADRO_CONSTANT));

   /**
    * A unit of mass equal to the mass of the electron
    * (standard name <code>me</code>).
    */
   public static final Unit<Mass> ELECTRON_MASS = intern(KILOGRAM.multiply(new BigDecimal(9.10938188e-31)));

   /**
    * A unit of mass equal to <code>453.59237 grams</code> (avoirdupois pound,
    * standard name <code>lb</code>).
    */
   public static final Unit<Mass> POUND = intern(KILOGRAM.multiply(AVOIRDUPOIS_POUND_DIVIDEND).divide(AVOIRDUPOIS_POUND_DIVISOR));

   /**
    * A unit of mass equal to <code>1/16 {@link #POUND}</code> (standard name
    * <code>gr</code>).
    */
   public static final Unit<Mass> OUNCE = intern(POUND.divide(16));

   /**
    * A unit of mass equal to <code>1 / 16 {@link #POUND}</code>
    * (standard name <code>oz</code>).
    */
   public static final Unit<Mass> GRAIN = intern(POUND.divide(7000));

   /**
    * A unit of mass equal to <code>2000 {@link #POUND}</code> (short ton,
    * standard name <code>ton</code>).
    */
   public static final Unit<Mass> TON = intern(POUND.multiply(2000));

   /**
    * A unit of mass equal to <code>1000 kg</code> (metric ton,
    * standard name <code>t</code>).
    */
   public static final Unit<Mass> METRIC_TON = intern(KILOGRAM.multiply(1000));





   /////////////////////
   // Electric charge //
   /////////////////////

   /**
    * A unit of electric charge equal to the charge on one electron
    * (standard name <code>e</code>).
    */
   public static final Unit<ElectricCharge> E = intern(COULOMB.multiply(ELEMENTARY_CHARGE));

   /**
    * A unit of electric charge equal to equal to the product of Avogadro's
    * number (see {@link SI#MOLE}) and the charge (1 e) on a single electron
    * (standard name <code>Fd</code>).
    */
   public static final Unit<ElectricCharge> FARADAY = intern(COULOMB.multiply(ELEMENTARY_CHARGE).multiply(AVOGADRO_CONSTANT)); // e/mol

   /**
    * A unit of electric charge which exerts a force of one dyne on an equal
    * charge at a distance of one centimeter
    * (standard name <code>Fr</code>).
    */
   public static final Unit<ElectricCharge> FRANKLIN = intern(COULOMB.multiply(new BigDecimal(3.3356e-10)));





   /////////////////
   // Temperature //
   /////////////////

   /**
    * A unit of temperature equal to <code>5/9 °K</code>
    * (standard name <code>°R</code>).
    */
   public static final Unit<Temperature> RANKINE = intern(KELVIN.multiply(5).divide(9));

   /**
    * A unit of temperature equal to degree Rankine minus
    * <code>459.67 °R</code> (standard name <code>°F</code>).
    *
    * @see #RANKINE
    */
   public static final Unit<Temperature> FAHRENHEIT = intern(RANKINE.add(new BigDecimal(459.67)));





   ///////////
   // Angle //
   ///////////

   /**
    * A unit of angle equal to a full circle or <code>2<i>&pi;</i>
    * {@link SI#RADIAN}</code> (standard name <code>rev</code>).
    */
   public static final Unit<Angle> REVOLUTION = intern(RADIAN.multiply(2).multiply(PI));

   /**
    * A unit of angle equal to <code>1/360 {@link #REVOLUTION}</code>
    * (standard name <code>°</code>).
    */
   public static final Unit<Angle> DEGREE_ANGLE = intern(REVOLUTION.divide(360));

   /**
    * A unit of angle equal to <code>1/60 {@link #DEGREE_ANGLE}</code>
    * (standard name <code>′</code>).
    */
   public static final Unit<Angle> MINUTE_ANGLE = intern(DEGREE_ANGLE.divide(60));

   /**
    * A unit of angle equal to <code>1/60 {@link #MINUTE_ANGLE}</code>
    * (standard name <code>"</code>).
    */
   public static final Unit<Angle> SECOND_ANGLE = intern(MINUTE_ANGLE.divide(60));

   /**
    * A unit of angle equal to <code>0.01 {@link SI#RADIAN}</code>
    * (standard name <code>centiradian</code>).
    */
   public static final Unit<Angle> CENTIRADIAN = intern(RADIAN.divide(100));

   /**
    * A unit of angle measure equal to <code>1/400 {@link #REVOLUTION}</code>
    * (standard name <code>grade</code>).
    */
   public static final Unit<Angle> GRADE = intern(REVOLUTION.divide(400));





   ///////////
   // Speed //
   ///////////

   /**
    * A unit of velocity expressing the number of international {@link
    * #MILE miles} per {@link SI#HOUR hour} (abbreviation <code>mph</code>).
    */
   public static final Unit<Speed> MILES_PER_HOUR = intern(NonSI.MILE.divide(SI.HOUR)).asType(Speed.class);


   /**
    * A unit of velocity expressing the number of  {@link #NAUTICAL_MILE
    * nautical miles} per {@link SI#HOUR hour} (abbreviation <code>kn</code>).
    */
   public static final Unit<Speed> KNOT = intern(NonSI.NAUTICAL_MILE.divide(SI.HOUR)).asType(Speed.class);

   /**
    * A unit of velocity to express the speed of an aircraft relative to
    * the speed of sound (standard name <code>Mach</code>).
    */
   public static final Unit<Speed> MACH = intern(METRES_PER_SECOND.multiply(new BigDecimal(331.6)));

   /**
    * A unit of velocity relative to the speed of light
    * (standard name <code>c</code>).
    */
   public static final Unit<Speed> C = intern(METRES_PER_SECOND.multiply(299792458));





   //////////////////
   // Acceleration //
   //////////////////

   /**
    * A unit of acceleration equal to the gravity at the earth's surface
    * (standard name <code>grav</code>).
    */
   public static final Unit<Acceleration> G = intern(METRES_PER_SQUARE_SECOND.multiply(STANDARD_GRAVITY_DIVIDEND).divide(STANDARD_GRAVITY_DIVISOR));




   //////////
   // Area //
   //////////

   /**
    * A unit of area equal to <code>43,560 ft²</code> (standard name <code>ft²</code>).
    */
   public static final Unit<Area> SQUARE_FOOT = intern(FOOT.pow(2).asType(Area.class));

   /**
    * A unit of area equal to <code>ft²</code> (standard name <code>ft²</code>).
    */
   public static final Unit<Area> ACRE = intern(SQUARE_FOOT.multiply(43560));

   /**
    * A unit of area equal to <code>100 m²</code> (standard name <code>a</code>).
    */
   public static final Unit<Area> ARE = intern(SQUARE_METRE.multiply(100));

   /**
    * A unit of area equal to <code>100 {@link #ARE}</code> (standard name <code>ha</code>).
    */
   public static final Unit<Area> HECTARE = intern(ARE.multiply(100)); // Exact.





   /////////////////
   // Data Amount //
   /////////////////

   /**
    * A unit of data amount equal to <code>8 {@link SI#BIT}</code>
    * (BinarY TErm, standard name <code>byte</code>).
    */
   public static final Unit<Data> BYTE = intern(BIT.multiply(8));

   /**
    * Equivalent {@link #BYTE}
    */
   public static final Unit<Data> OCTET = BYTE;




   //////////////////////
   // Electric current //
   //////////////////////

   /**
    * A unit of electric charge equal to the centimeter-gram-second
    * electromagnetic unit of magnetomotive force, equal to <code>10/4
    * &pi;ampere-turn</code> (standard name <code>Gi</code>).
    */
   public static final Unit<ElectricCurrent> GILBERT = intern(SI.AMPERE.multiply(10).divide(4).divide(PI));




   ////////////
   // Energy //
   ////////////

   /**
    * A unit of energy equal to <code>1E-7 J</code>
    * (standard name <code>erg</code>).
    */
   public static final Unit<Energy> ERG = intern(JOULE.divide(10000000));

   /**
    * A unit of energy equal to one electron-volt (standard name
    * <code>eV</code>, also recognized <code>keV, MeV, GeV</code>).
    */
   public static final Unit<Energy> ELECTRON_VOLT = intern(JOULE.multiply(ELEMENTARY_CHARGE));

   /**
    * A unit used to measure the ionizing ability of radiation
    * (standard name <code>Roentgen</code>).
    */
   public static final Unit<Energy> CALORIE = intern(JOULE.multiply(4184).divide(1000));

   /**
    * A unit used to measure the ionizing ability of radiation
    * (standard name <code>Roentgen</code>).
    */
   public static final Unit<Energy> BTU = intern(JOULE.multiply(105735).divide(100000));



   /////////////////
   // Illuminance //
   /////////////////

   /**
    * A unit of illuminance equal to <code>1E4 Lx</code>
    * (standard name <code>La</code>).
    */
   public static final Unit<Illuminance> LAMBERT = intern(LUX.multiply(10000));




   ///////////////////
   // Magnetic Flux //
   ///////////////////

   /**
    * A unit of magnetic flux equal <code>1E-8 Wb</code>
    * (standard name <code>Mx</code>).
    */
   public static final Unit<MagneticFlux> MAXWELL = intern(WEBER.divide(100000000));




   ///////////////////////////
   // Magnetic Flux Density //
   ///////////////////////////

   /**
    * A unit of magnetic flux density equal <code>1000 A/m</code>
    * (standard name <code>G</code>).
    */
   public static final Unit<MagneticFluxDensity> GAUSS = intern(TESLA.divide(10000));





   ///////////
   // Force //
   ///////////

   /**
    * A unit of force equal to <code>1E-5 N</code>
    * (standard name <code>dyn</code>).
    */
   public static final Unit<Force> DYNE = intern(NEWTON.divide(100000));

   /**
    * A unit of force equal to <code>9.80665 N</code>
    * (standard name <code>kgf</code>).
    */
   public static final Unit<Force> KILOGRAM_FORCE = intern(NEWTON.multiply(STANDARD_GRAVITY_DIVIDEND).divide(STANDARD_GRAVITY_DIVISOR));

   /**
    * A unit of force equal to <code>{@link #POUND}·{@link #G}</code>
    * (standard name <code>lbf</code>).
    */
   public static final Unit<Force> POUND_FORCE = intern(NEWTON.multiply(1L * AVOIRDUPOIS_POUND_DIVIDEND * STANDARD_GRAVITY_DIVIDEND).divide(1L * AVOIRDUPOIS_POUND_DIVISOR * STANDARD_GRAVITY_DIVISOR));




   ///////////
   // Power //
   ///////////

   /**
    * A unit of power equal to the power required to raise a mass of 75
    * kilograms at a velocity of 1 meter per second (metric,
    * standard name <code>hp</code>).
    */
   public static final Unit<Power> HORSEPOWER = intern(WATT.multiply(new BigDecimal(735.499)));




   //////////////
   // Pressure //
   //////////////

   /**
    * A unit of pressure equal to the average pressure of the Earth's
    * atmosphere at sea level (standard name <code>atm</code>).
    */
   public static final Unit<Pressure> ATMOSPHERE = intern(PASCAL.multiply(101325));

   /**
    * A unit of pressure equal to <code>100 kPa</code>
    * (standard name <code>bar</code>).
    */
   public static final Unit<Pressure> BAR = intern(PASCAL.multiply(100000));

   /**
    * A unit of pressure equal to the pressure exerted at the Earth's
    * surface by a column of mercury 1 millimeter high
    * (standard name <code>mmHg</code>).
    */
   public static final Unit<Pressure> MILLIMETER_OF_MERCURY = intern(PASCAL.multiply(new BigDecimal(133.322)));

   /**
    * A unit of pressure equal to the pressure exerted at the Earth's
    * surface by a column of mercury 1 inch high
    * (standard name <code>inHg</code>).
    */
   public static final Unit<Pressure> INCH_OF_MERCURY = intern(PASCAL.multiply(new BigDecimal(3386.388)));

   /**
    * A unit of pressure equal to the pressure exerted at the Earth's
    * surface by a column of mercury 1 inch high
    * (standard name <code>inHg</code>).
    */
   public static final Unit<Pressure> POUND_PER_SQUARE_INCH = intern(PASCAL.multiply(68948).divide(10)); // psi




   /////////////////////////////
   // Radiation dose absorbed //
   /////////////////////////////

   /**
    * A unit of radiation dose absorbed equal to a dose of 0.01 joule of
    * energy per kilogram of mass (J/kg) (standard name <code>rd</code>).
    */
   public static final Unit<RadiationDoseAbsorbed> RAD = intern(GRAY.divide(100));

   /**
    * A unit of radiation dose effective equal to <code>0.01 Sv</code>
    * (standard name <code>rem</code>).
    */
   public static final Unit<RadiationDoseEffective> REM = intern(SIEVERT.divide(100));




   //////////////////////////
   // Radioactive activity //
   //////////////////////////

   /**
    * A unit of radioctive activity equal to the activity of a gram of radium
    * (standard name <code>Ci</code>).
    */
   public static final Unit<Radioactivity> CURIE = intern(BECQUEREL.multiply(37000000000L));

   /**
    * A unit of radioctive activity equal to 1 million radioactive
    * disintegrations per second (standard name <code>Rd</code>).
    */
   public static final Unit<Radioactivity> RUTHERFORD = intern(SI.BECQUEREL.multiply(1000000));




   /////////////////
   // Solid angle //
   /////////////////

   /**
    * A unit of solid angle equal to <code>4 <i>&pi;</i> steradians</code>
    * (standard name <code>sphere</code>).
    */
   public static final Unit<SolidAngle> SPHERE = intern(STERADIAN.multiply(4).multiply(PI));




   ////////////
   // Volume //
   ////////////


   /**
    * A unit of volume equal to one cubic inch (<code>in³</code>).
    */
   public static final Unit<Volume> CUBIC_INCH = intern(INCH.pow(3).asType(Volume.class));

   /**
    * A unit of volume equal to one cubic foot (<code>ft³</code>).
    */
   public static final Unit<Volume> CUBIC_FOOT = intern(CUBIC_INCH.multiply(1728).asType(Volume.class));




   /**
    * A unit of volume equal to one US gallon, The US gallon is based on the Queen
    * Anne or Wine gallon occupying 231 cubic inches (standard name <code>gal</code>).
    */
   public static final Unit<Volume> GALLON = intern(CUBIC_INCH.multiply(231));

   /**
    * A unit of volume equal to one quarter a US Gallon (default label <code>qt</code>).
    */
   public static final Unit<Volume> QUART = intern(GALLON.divide(4));

   /**
    * A unit of volume equal to half a US Quart (default label <code>pt</code>).
    */
   public static final Unit<Volume> PINT = intern(QUART.divide(2));

   /**
    * A unit of volume equal to half a US Pint (default label <code>cup</code>).
    */
   public static final Unit<Volume> CUP = intern(QUART.divide(4));

   /**
    * A unit of volume equal to <code>1 / 128</code> a US Gallon (standard name <code>fl_oz</code>).
    */
   public static final Unit<Volume> FLUID_OUNCE = intern(GALLON.divide(128));

   /**
    * A unit of volume equal to half a US Fluid Ounce (default label <code>Tbsp</code>).
    */
   public static final Unit<Volume> TABLESPOON = intern(FLUID_OUNCE.divide(2));

   /**
    * A unit of volume equal to a third a US Tablespoon (default label <code>tsp</code>).
    */
   public static final Unit<Volume> TEASPOON = intern(TABLESPOON.divide(3));




   /**
    * A unit of volume equal to 2150.42 cubic inches. (default label <code>bu</code>).
    */
   public static final Unit<Volume> BUSHEL = intern(CUBIC_INCH.multiply(21504200).divide(10000));

   /**
    * A unit of volume equal to 128 cubic feet (default label <code>cord</code>).
    */
   public static final Unit<Volume> CORD = intern(CUBIC_FOOT.multiply(128));









   ///////////////
   // Viscosity //
   ///////////////

   /**
    * A unit of dynamic viscosity equal to <code>1 g/(cm·s)</code>
    * (cgs unit).
    */
   @SuppressWarnings("unchecked")
   public static final Unit<DynamicViscosity> POISE = intern((Unit<DynamicViscosity>) GRAM.divide(CENTI(METRE).multiply(SECOND)));

   /**
    * A unit of kinematic viscosity equal to <code>1 cm²/s</code> (cgs unit).
    */
   @SuppressWarnings("unchecked")
   public static final Unit<KinematicViscosity> STOKE = intern((Unit<KinematicViscosity>) CENTI(METRE).pow(2).divide(SECOND));







   ////////////
   // Others //
   ////////////

   /**
    * A unit used to measure the ionizing ability of radiation
    * (standard name <code>Roentgen</code>).
    */
   public static final Unit<IonizingRadiation> ROENTGEN = intern(COULOMB.multiply(new BigDecimal(2.58e-4)).divide(KILOGRAM).asType(IonizingRadiation.class));




   /////////////////////
   // Collection View //
   /////////////////////

   /**
    * Returns a read only view over the units defined in this class.
    *
    * @return the collection of NonSI units.
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