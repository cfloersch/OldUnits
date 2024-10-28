package xpertss.measure.unit;

import xpertss.measure.quantity.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains SI (Système International d'Unités) base units,
 * and derived units.
 *
 * @see <a href="http://en.wikipedia.org/wiki/SI">Wikipedia: SI</a>
 */
public final class SI extends SystemOfUnits {

   /**
    * Holds collection of SI units.
    */
   private static HashSet<Unit<?>> UNITS = new HashSet<>();

   /**
    * Default constructor (prevents this class from being instantiated).
    */
   private SI()
   {
   }





   ////////////////
   // BASE UNITS //
   ////////////////

   /**
    * The base unit for electric current quantities (<code>A</code>).
    * The Ampere is that constant current which, if maintained in two straight
    * parallel conductors of infinite length, of negligible circular
    * cross-section, and placed 1 metre apart in vacuum, would produce between
    * these conductors a force equal to 2 × 10-7 newton per metre of length.
    * It is named after the French physicist Andre Ampere (1775-1836).
    */
   public static final BaseUnit<ElectricCurrent> AMPERE = intern(new BaseUnit<ElectricCurrent>("A"));

   /**
    * The base unit for luminous intensity quantities (<code>cd</code>).
    * The candela is the luminous intensity, in a given direction,
    * of a source that emits monochromatic radiation of frequency
    * 540 × 1012 hertz and that has a radiant intensity in that
    * direction of 1/683 watt per steradian
    *
    * @see <a href="http://en.wikipedia.org/wiki/Candela">
    *      Wikipedia: Candela</a>
    */
   public static final BaseUnit<LuminousIntensity> CANDELA = intern(new BaseUnit<LuminousIntensity>("cd"));

   /**
    * The base unit for thermodynamic temperature quantities (<code>K</code>).
    * The kelvin is the 1/273.16th of the thermodynamic temperature of the
    * triple point of water. It is named after the Scottish mathematician and
    * physicist William Thomson 1st Lord Kelvin (1824-1907)
    */
   public static final BaseUnit<Temperature> KELVIN = intern(new BaseUnit<Temperature>("K"));

   /**
    * The base unit for mass quantities (<code>kg</code>).
    * It is the only SI unit with a prefix as part of its name and symbol.
    * The kilogram is equal to the mass of an international prototype in the
    * form of a platinum-iridium cylinder kept at Sevres in France.
    *
    * @see #GRAM
    */
   public static final BaseUnit<Mass> KILOGRAM = intern(new BaseUnit<Mass>("kg"));

   /**
    * The base unit for length quantities (<code>m</code>).
    * One meter was redefined in 1983 as the distance traveled by light in
    * a vacuum in 1/299,792,458 of a second.
    */
   public static final BaseUnit<Length> METRE = intern(new BaseUnit<Length>("m"));

   /**
    * Equivalent to {@link #METRE} (American spelling).
    */
   public static final Unit<Length> METER = METRE;

   /**
    * The base unit for amount of substance quantities (<code>mol</code>).
    * The mole is the amount of substance of a system which contains as many
    * elementary entities as there are atoms in 0.012 kilogram of carbon 12.
    */
   public static final BaseUnit<AmountOfSubstance> MOLE = intern(new BaseUnit<AmountOfSubstance>("mol"));

   /**
    * The base unit for duration quantities (<code>s</code>).
    * It is defined as the duration of 9,192,631,770 cycles of radiation
    * corresponding to the transition between two hyperfine levels of
    * the ground state of cesium (1967 Standard).
    */
   public static final BaseUnit<Time> SECOND = intern(new BaseUnit<Time>("s"));



   ////////////////////////////////
   // SI DERIVED ALTERNATE UNITS //
   ////////////////////////////////

   /**
    * The derived unit for mass quantities (<code>g</code>).
    * The base unit for mass quantity is {@link #KILOGRAM}.
    */
   public static final Unit<Mass> GRAM = KILOGRAM.divide(1000);


   /**
    * The unit for plane angle quantities (<code>rad</code>).
    * One radian is the angle between two radii of a circle such that the
    * length of the arc between them is equal to the radius.
    */
   public static final AlternateUnit<Angle> RADIAN = intern(new AlternateUnit<Angle>("rad", Unit.ONE));

   /**
    * The unit for solid angle quantities (<code>sr</code>).
    * One steradian is the solid angle subtended at the center of a sphere by
    * an area on the surface of the sphere that is equal to the radius squared.
    * The total solid angle of a sphere is 4*Pi steradians.
    */
   public static final AlternateUnit<SolidAngle> STERADIAN = intern(new AlternateUnit<SolidAngle>("sr", Unit.ONE));

   /**
    * The unit for binary information (<code>bit</code>).
    */
   public static final AlternateUnit<Data> BIT = intern(new AlternateUnit<Data>("bit", Unit.ONE));

   /**
    * The derived unit for frequency (<code>Hz</code>).
    * A unit of frequency equal to one cycle per second.
    * After Heinrich Rudolf Hertz (1857-1894), German physicist who was the
    * first to produce radio waves artificially.
    */
   public static final AlternateUnit<Frequency> HERTZ = intern(new AlternateUnit<Frequency>("Hz", Unit.ONE.divide(SECOND)));

   /**
    * The derived unit for force (<code>N</code>).
    * One newton is the force required to give a mass of 1 kilogram an Force
    * of 1 metre per second per second. It is named after the English
    * mathematician and physicist Sir Isaac Newton (1642-1727).
    */
   public static final AlternateUnit<Force> NEWTON = intern(new AlternateUnit<Force>("N", METRE.multiply(KILOGRAM).divide(SECOND.pow(2))));

   /**
    * The derived unit for pressure, stress (<code>Pa</code>).
    * One pascal is equal to one newton per square meter. It is named after
    * the French philosopher and mathematician Blaise Pascal (1623-1662).
    */
   public static final AlternateUnit<Pressure> PASCAL = intern(new AlternateUnit<Pressure>("Pa", NEWTON.divide(METRE.pow(2))));

   /**
    * The derived unit for energy, work, quantity of heat (<code>J</code>).
    * One joule is the amount of work done when an applied force of 1 newton
    * moves through a distance of 1 metre in the direction of the force.
    * It is named after the English physicist James Prescott Joule (1818-1889).
    */
   public static final AlternateUnit<Energy> JOULE = intern(new AlternateUnit<Energy>("J", NEWTON.multiply(METRE)));

   /**
    * The derived unit for power, radiant, flux (<code>W</code>).
    * One watt is equal to one joule per second. It is named after the British
    * scientist James Watt (1736-1819).
    */
   public static final AlternateUnit<Power> WATT = intern(new AlternateUnit<Power>("W", JOULE.divide(SECOND)));

   /**
    * The derived unit for electric charge, quantity of electricity
    * (<code>C</code>).
    * One Coulomb is equal to the quantity of charge transferred in one second
    * by a steady current of one ampere. It is named after the French physicist
    * Charles Augustin de Coulomb (1736-1806).
    */
   public static final AlternateUnit<ElectricCharge> COULOMB = intern(new AlternateUnit<ElectricCharge>("C", SECOND.multiply(AMPERE)));

   /**
    * The derived unit for electric potential difference, electromotive force
    * (<code>V</code>).
    * One Volt is equal to the difference of electric potential between two
    * points on a conducting wire carrying a constant current of one ampere
    * when the power dissipated between the points is one watt. It is named
    * after the Italian physicist Count Alessandro Volta (1745-1827).
    */
   public static final AlternateUnit<ElectricPotential> VOLT = intern(new AlternateUnit<ElectricPotential>("V", WATT.divide(AMPERE)));

   /**
    * The derived unit for capacitance (<code>F</code>).
    * One Farad is equal to the capacitance of a capacitor having an equal
    * and opposite charge of 1 coulomb on each plate and a potential difference
    * of 1 volt between the plates. It is named after the British physicist
    * and chemist Michael Faraday (1791-1867).
    */
   public static final AlternateUnit<ElectricCapacitance> FARAD = intern(new AlternateUnit<ElectricCapacitance>("F", COULOMB.divide(VOLT)));

   /**
    * The derived unit for electric resistance (<code>Ω</code> or
    * <code>Ohm</code>).
    * One Ohm is equal to the resistance of a conductor in which a current of
    * one ampere is produced by a potential of one volt across its terminals.
    * It is named after the German physicist Georg Simon Ohm (1789-1854).
    */
   public static final AlternateUnit<ElectricResistance> OHM = intern(new AlternateUnit<ElectricResistance>("Ω", VOLT.divide(AMPERE)));

   /**
    * The derived unit for electric conductance (<code>S</code>).
    * One Siemens is equal to one ampere per volt. It is named after
    * the German engineer Ernst Werner von Siemens (1816-1892).
    */
   public static final AlternateUnit<ElectricConductance> SIEMENS = intern(new AlternateUnit<ElectricConductance>("S", AMPERE.divide(VOLT)));

   /**
    * The derived unit for magnetic flux (<code>Wb</code>).
    * One Weber is equal to the magnetic flux that in linking a circuit of one
    * turn produces in it an electromotive force of one volt as it is uniformly
    * reduced to zero within one second. It is named after the German physicist
    * Wilhelm Eduard Weber (1804-1891).
    */
   public static final AlternateUnit<MagneticFlux> WEBER = intern(new AlternateUnit<MagneticFlux>("Wb", VOLT.multiply(SECOND)));

   /**
    * The SI unit for magnetomotive force (standard name <code>At</code>).
    */
   public static final AlternateUnit<MagnetomotiveForce> AMPERE_TURN = intern(new AlternateUnit<MagnetomotiveForce>("At", AMPERE));

   /**
    * The derived unit for magnetic flux density (<code>T</code>).
    * One Tesla is equal equal to one weber per square meter. It is named
    * after the Serbian-born American electrical engineer and physicist
    * Nikola Tesla (1856-1943).
    */
   public static final AlternateUnit<MagneticFluxDensity> TESLA = intern(new AlternateUnit<MagneticFluxDensity>("T", WEBER.divide(METRE.pow(2))));

   /**
    * The derived unit for inductance (<code>H</code>).
    * One Henry is equal to the inductance for which an induced electromotive
    * force of one volt is produced when the current is varied at the rate of
    * one ampere per second. It is named after the American physicist
    * Joseph Henry (1791-1878).
    */
   public static final AlternateUnit<ElectricInductance> HENRY = intern(new AlternateUnit<ElectricInductance>("H", WEBER.divide(AMPERE)));

   /**
    * The derived unit for Celsius temperature (<code>℃</code>).
    * This is a unit of temperature such as the freezing point of water
    * (at one atmosphere of pressure) is 0 ℃, while the boiling point is
    * 100 ℃.
    */
   public static final Unit<Temperature> CELSIUS = intern(KELVIN.add(new BigDecimal(273.15)));

   /**
    * The derived unit for luminous flux (<code>lm</code>).
    * One Lumen is equal to the amount of light given out through a solid angle
    * by a source of one candela intensity radiating equally in all directions.
    */
   public static final AlternateUnit<LuminousFlux> LUMEN = intern(new AlternateUnit<LuminousFlux>("lm", CANDELA.multiply(STERADIAN)));

   /**
    * The derived unit for illuminance (<code>lx</code>).
    * One Lux is equal to one lumen per square meter.
    */
   public static final AlternateUnit<Illuminance> LUX = intern(new AlternateUnit<Illuminance>("lx", LUMEN.divide(METRE.pow(2))));

   /**
    * The derived unit for activity of a radionuclide (<code>Bq</code>).
    * One becquerel is the radiation caused by one disintegration per second.
    * It is named after the French physicist, Antoine-Henri Becquerel
    * (1852-1908).
    */
   public static final AlternateUnit<Radioactivity> BECQUEREL = intern(new AlternateUnit<Radioactivity>("Bq", Unit.ONE.divide(SECOND)));

   /**
    * The derived unit for absorbed dose, specific energy (imparted), kerma
    * (<code>Gy</code>).
    * One gray is equal to the dose of one joule of energy absorbed per one
    * kilogram of matter. It is named after the British physician
    * L. H. Gray (1905-1965).
    */
   public static final AlternateUnit<RadiationDoseAbsorbed> GRAY = intern(new AlternateUnit<RadiationDoseAbsorbed>("Gy", JOULE.divide(KILOGRAM)));

   /**
    * The derived unit for dose equivalent (<code>Sv</code>).
    * One Sievert is equal  is equal to the actual dose, in grays, multiplied
    * by a "quality factor" which is larger for more dangerous forms of
    * radiation. It is named after the Swedish physicist Rolf Sievert
    * (1898-1966).
    */
   public static final AlternateUnit<RadiationDoseEffective> SIEVERT = intern(new AlternateUnit<RadiationDoseEffective>("Sv", JOULE.divide(KILOGRAM)));

   /**
    * The derived unit for catalytic activity (<code>kat</code>).
    */
   public static final AlternateUnit<CatalyticActivity> KATAL = intern(new AlternateUnit<CatalyticActivity>("kat", MOLE.divide(SECOND)));






   //////////////////////////////
   // SI DERIVED PRODUCT UNITS //
   //////////////////////////////

   /**
    * The metric unit for velocity quantities (<code>m/s</code>).
    */
   public static final Unit<Speed> METRES_PER_SECOND = intern(new ProductUnit<Speed>(METRE.divide(SECOND)));

   /**
    * Equivalent to {@link #METRES_PER_SECOND}.
    */
   public static final Unit<Speed> METERS_PER_SECOND = METRES_PER_SECOND;

   /**
    * The metric unit for acceleration quantities (<code>m/s²</code>).
    */
   public static final Unit<Acceleration> METRES_PER_SQUARE_SECOND = intern(new ProductUnit<Acceleration>(METRES_PER_SECOND.divide(SECOND)));

   /**
    * Equivalent to {@link #METRES_PER_SQUARE_SECOND}.
    */
   public static final Unit<Acceleration> METERS_PER_SQUARE_SECOND = METRES_PER_SQUARE_SECOND;

   /**
    * The metric unit for area quantities (<code>m²</code>).
    */
   public static final Unit<Area> SQUARE_METRE = intern(new ProductUnit<Area>(METRE.multiply(METRE)));

   /**
    * Equivalent to {@link #SQUARE_METRE}.
    */
   public static final Unit<Area> SQUARE_METER = SQUARE_METRE;

   /**
    * The metric unit for volume quantities (<code>m³</code>).
    */
   public static final Unit<Volume> CUBIC_METRE = intern(new ProductUnit<Volume>(SQUARE_METRE.multiply(METRE)));

   /**
    * Equivalent to {@link #CUBIC_METRE}.
    */
   public static final Unit<Volume> CUBIC_METER = CUBIC_METRE;



   /**
    * The metric unit for action quantities (<code>J.s</code>).
    */
   public static final Unit<Action> JOULE_SECOND = intern(new ProductUnit<Action>(JOULE.multiply(SECOND)));


   /**
    * The SI unit for electric permittivity quantities (standard name <code>F/m</code>).
    */
   public static final Unit<ElectricPermittivity> FARADS_PER_METRE = intern(new ProductUnit<ElectricPermittivity>(FARAD.divide(METRE)));


   /**
    * The SI unit for ionizing radiation quantities (standard name <code>C/kg"</code>).
    */
   public static final Unit<IonizingRadiation> COULOMBS_PER_KILOGRAM = intern(new ProductUnit<IonizingRadiation>(COULOMB.divide(KILOGRAM)));


   /**
    * The SI unit for luminance quantities (standard name <code>cd/m2</code>).
    */
   public static final Unit<Luminance> CANDELAS_PER_SQUARE_METRE = intern(new ProductUnit<Luminance>(CANDELA.divide(SQUARE_METRE)));

   /**
    * @see #CANDELAS_PER_SQUARE_METRE
    */
   public static final Unit<Luminance> CANDELAS_PER_SQUARE_METER = CANDELAS_PER_SQUARE_METRE;

   /**
    * The SI unit for magnetic field strength quantities (standard name <code>A/m"</code>).
    */
   public static final Unit<MagneticFieldStrength> AMPERES_PER_METRE = intern(new ProductUnit<MagneticFieldStrength>(AMPERE.divide(METRE)));

   /**
    * @see #AMPERES_PER_METRE
    */
   public static final Unit<MagneticFieldStrength> AMPERES_PER_METER = AMPERES_PER_METRE;



   /**
    * The SI unit for magnetic permeability quantities (standard name <code>N/A2</code>).
    */
   public static final Unit<MagneticPermeability> NEWTONS_PER_SQUARE_AMPERE = intern(new ProductUnit<MagneticPermeability>(NEWTON.divide(AMPERE.pow(2))));


   /**
    * The SI unit for wave number quantities (standard name <code>1/m</code>).
    */
   public static final Unit<WaveNumber> RECIPROCAL_METRE = intern(new ProductUnit<WaveNumber>(METRE.pow(-1)));




   /**
    * The SI unit for dynamic viscosity quantities (standard name <code>Pa.s</code>).
    */
   public static final Unit<DynamicViscosity> PASCAL_SECOND = intern(new ProductUnit<DynamicViscosity>(PASCAL.multiply(SECOND)));


   /**
    * The SI unit for kinematic viscosity quantities (standard name <code>m2/s"</code>).
    */
   public static final Unit<KinematicViscosity> SQUARE_METRES_PER_SECOND = intern(new ProductUnit<KinematicViscosity>(SQUARE_METRE.divide(SECOND)));


   /**
    * The SI unit for binary data rate (standard name <code>bit/s</code>).
    */
   public static final ProductUnit<DataRate> BITS_PER_SECOND = intern(new ProductUnit<DataRate>(BIT.divide(SECOND)));




   /**
    * A unit of duration equal to <code>60 s</code>
    * (standard name <code>min</code>).
    */
   public static final Unit<Time> MINUTE = intern(SECOND.multiply(60));

   /**
    * A unit of duration equal to <code>60 {@link #MINUTE}</code>
    * (standard name <code>h</code>).
    */
   public static final Unit<Time> HOUR = intern(MINUTE.multiply(60));




   /**
    * A unit of volume equal to one cubic decimeter (default label
    * <code>L</code>, also recognized <code>µL, mL, cL, dL</code>).
    */
   public static final Unit<Volume> LITRE = intern(CUBIC_METRE.divide(1000));


   /**
    * Equivalent to {@link #LITRE} (American spelling).
    */
   public static final Unit<Volume> LITER = LITRE;


   /**
    * A unit of velocity expressing the number of KILO({@link SI#METRE}) per
    * {@link SI#HOUR hour}.
    */
   public static final Unit<Speed> KILOMETRES_PER_HOUR = intern(METRE.multiply(1000).divide(HOUR)).asType(Speed.class);

   /**
    * Equivalent to {@link #KILOMETRES_PER_HOUR}.
    */
   public static final Unit<Speed> KILOMETERS_PER_HOUR = KILOMETRES_PER_HOUR;








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