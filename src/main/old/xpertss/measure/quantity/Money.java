package xpertss.measure.quantity;


import xpertss.measure.unit.BaseUnit;

/**
 * This interface represents something generally accepted as a medium of
 * exchange, a measure of value, or a means of payment. The units for money
 * quantities is of type {@link xpertss.measure.unit.CurrencyUnit}.
 */
public interface Money extends Quantity {

   /**
    * Holds the base unit for money quantities (symbol "�", currency symbol).
    */
   public final static BaseUnit<Money> BASE_UNIT = new BaseUnit<Money>("\u00A4");


}
