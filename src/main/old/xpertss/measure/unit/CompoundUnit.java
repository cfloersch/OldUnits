package xpertss.measure.unit;

import xpertss.measure.converter.UnitConverter;
import xpertss.measure.quantity.Quantity;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represents the multi-radix units (such as "hour:min:sec"). Instances of
 * this class are created using the {@link Unit#compound Unit.compound} method.
 * <p/>
 * Examples of compound units:[code]
 * Unit<Time> HOUR_MINUTE_SECOND = HOUR.compound(MINUTE).compound(SECOND);
 * Unit<Angle> DEGREE_MINUTE_ANGLE = DEGREE_ANGLE.compound(MINUTE_ANGLE);
 * [/code]
 */
public final class CompoundUnit<Q extends Quantity> extends DerivedUnit<Q> {

   /**
    * Holds the higher unit.
    */
   private final Unit<Q> high;

   /**
    * Holds the lower unit.
    */
   private final Unit<Q> low;

   /**
    * Creates a compound unit from the specified units.
    *
    * @param high the high unit.
    * @param low  the lower unit(s)
    * @throws IllegalArgumentException if both units do not the same system
    *                                  unit.
    */
   CompoundUnit(Unit<Q> high, Unit<Q> low)
   {
      if(!high.getStandardUnit().equals(low.getStandardUnit())) {
         throw new IllegalArgumentException("Both units do not have the same standard unit");
      }
      this.high = Objects.requireNonNull(high);
      this.low = Objects.requireNonNull(low);
   }

   /**
    * Returns the lower unit of this compound unit.
    *
    * @return the lower unit.
    */
   public Unit<Q> getLower()
   {
      return low;
   }

   /**
    * Returns the higher unit of this compound unit.
    *
    * @return the higher unit.
    */
   public Unit<Q> getHigher()
   {
      return high;
   }



   List<Unit<?>> collect(List<Unit<?>> units)
   {
      if(high instanceof CompoundUnit) {
         CompoundUnit<?> highUnit = (CompoundUnit<?>) high;
         highUnit.collect(units);
      } else {
         units.add(high);
      }
      if(low instanceof CompoundUnit) {
         CompoundUnit<?> lowUnit = (CompoundUnit<?>) low;
         lowUnit.collect(units);
      } else {
         units.add(low);
      }
      return units;
   }

   /**
    * Indicates if this compound unit is considered equals to the specified
    * object (both are compound units with same composing units in the
    * same order).
    * <p>
    *
    * @param that the object to compare for equality.
    * @return <code>true</code> if <code>this</code> and <code>that</code>
    *         are considered equals; <code>false</code>otherwise.
    */
   public boolean equals(Object that)
   {
      if(this == that) return true;
      if(that instanceof CompoundUnit) {
         CompoundUnit<?> thatUnit = (CompoundUnit<?>) that;
         return collect(new ArrayList<Unit<?>>()).equals(thatUnit.collect(new ArrayList<Unit<?>>()));
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      return high.hashCode() ^ low.hashCode();
   }

   @Override
   public Unit<? super Q> getStandardUnit()
   {
      return low.getStandardUnit();
   }

   @Override
   public UnitConverter toStandardUnit()
   {
      return low.toStandardUnit();
   }

   private static final long serialVersionUID = 1L;
}