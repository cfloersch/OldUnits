/**
 * Created By: cfloersch
 * Date: 2/17/2015
 * Copyright 2013 XpertSoftware
 */
package xpertss.unit.converters;

import org.junit.Test;

import org.xpertss.measure.UnitConverter;

import static java.math.BigDecimal.ONE;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PiConverterTest {


   @Test
   public void testForwardBackward() {

      UnitConverter converter = new PiMultiplierConverter();
      UnitConverter inverted = converter.inverse();
      assertEquals(ONE, inverted.convert(converter.convert(ONE)));
   }

   @Test
   public void testEquals() {
      UnitConverter converter = new PiMultiplierConverter();
      UnitConverter inverted = converter.inverse();
      assertFalse(converter.equals(inverted));
   }

   @Test
   public void testHashcode() {
      UnitConverter converter = new PiMultiplierConverter();
      UnitConverter inverted = converter.inverse();
      assertFalse(converter.hashCode() == inverted.hashCode());
   }

   @Test
   public void testIsLinear() {
      UnitConverter converter = new PiMultiplierConverter();
      assertTrue(converter.isLinear());
      UnitConverter inverted = converter.inverse();
      assertTrue(inverted.isLinear());
   }

}
