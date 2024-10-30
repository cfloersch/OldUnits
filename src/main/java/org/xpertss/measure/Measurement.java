package org.xpertss.measure;


import java.math.BigDecimal;

// TODO This is basically the Measure and DecimalMeasurable wrapped into one
// This can be wrapped up in Quantity without the need for a seperate measure class

public abstract class Measurement<Q extends Quantity<Q>> implements Comparable<Measurement<Q>> {


   /**
    * Returns the unit of this measurable.
    *
    * @return the measurement unit.
    */
   public abstract Unit<Q> getUnit();

   /**
    * Returns the value of this measurable as a <code>BigDecimal</code>.
    *
    * @return the measurement value.
    */
   public abstract BigDecimal getValue();

   /**
    * Returns the value of this measurable stated in the specified unit as
    * a <code>BigDecimal</code>.
    *
    * @param unit the unit in which this measurable value is stated.
    * @return the numeric value after conversion.
    */
   public abstract BigDecimal getValue(Unit<Q> unit);

   /**
    * Returns the measure equivalent to this measure but stated in the
    * specified unit.
    *
    * @param unit the new measurement unit.
    * @return the measure stated in the specified unit.
    */
   public abstract Measurement<Q> getAs(Unit<Q> unit);


   @Override
   public int compareTo(Measurement<Q> o)
   {
      return 0;
   }


   // equivalency methods?

   public static <Q extends Quantity<Q>> Measurement<Q> of(BigDecimal value, Unit<Q> unit)
   {
      return null;
   }

   public static <Q extends Quantity<Q>> Measurement<Q> of(double value, Unit<Q> unit)
   {
      return null;
   }

   public static <Q extends Quantity<Q>> Measurement<Q> of(long value, Unit<Q> unit)
   {
      return null;
   }

   // TODO Add a creater for BigInteger??

}
