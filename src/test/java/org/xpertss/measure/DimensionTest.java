package org.xpertss.measure;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.xpertss.measure.Dimension.*;


public class DimensionTest {

   @Test
   public void testNone() {
      assertEquals(NONE, NONE);
      assertFalse(NONE.equals(TIME));
      assertFalse(NONE.equals(ELECTRIC_CURRENT));
      assertFalse(NONE.equals(LENGTH));
      assertFalse(NONE.equals(MASS));
      assertFalse(NONE.equals(TEMPERATURE));
      assertFalse(NONE.equals(AMOUNT_OF_SUBSTANCE));
      assertFalse(NONE.equals(LUMINOUS_INTENSITY));
   }

   @Test
   public void testTime() {
      assertEquals(TIME, TIME);
      assertFalse(TIME.equals(NONE));
      assertFalse(TIME.equals(ELECTRIC_CURRENT));
      assertFalse(TIME.equals(LENGTH));
      assertFalse(TIME.equals(MASS));
      assertFalse(TIME.equals(TEMPERATURE));
      assertFalse(TIME.equals(AMOUNT_OF_SUBSTANCE));
      assertFalse(TIME.equals(LUMINOUS_INTENSITY));
      assertEquals("[T]", TIME.toString());
   }

   @Test
   public void testElectricCurrent() {
      assertEquals(ELECTRIC_CURRENT, ELECTRIC_CURRENT);
      assertFalse(ELECTRIC_CURRENT.equals(NONE));
      assertFalse(ELECTRIC_CURRENT.equals(TIME));
      assertFalse(ELECTRIC_CURRENT.equals(LENGTH));
      assertFalse(ELECTRIC_CURRENT.equals(MASS));
      assertFalse(ELECTRIC_CURRENT.equals(TEMPERATURE));
      assertFalse(ELECTRIC_CURRENT.equals(AMOUNT_OF_SUBSTANCE));
      assertFalse(ELECTRIC_CURRENT.equals(LUMINOUS_INTENSITY));
      assertEquals("[I]", ELECTRIC_CURRENT.toString());
   }

   @Test
   public void testLength() {
      assertEquals(LENGTH, LENGTH);
      assertFalse(LENGTH.equals(NONE));
      assertFalse(LENGTH.equals(TIME));
      assertFalse(LENGTH.equals(ELECTRIC_CURRENT));
      assertFalse(LENGTH.equals(MASS));
      assertFalse(LENGTH.equals(TEMPERATURE));
      assertFalse(LENGTH.equals(AMOUNT_OF_SUBSTANCE));
      assertFalse(LENGTH.equals(LUMINOUS_INTENSITY));
      assertEquals("[L]", LENGTH.toString());
   }

   @Test
   public void testMass() {
      assertEquals(MASS, MASS);
      assertFalse(MASS.equals(NONE));
      assertFalse(MASS.equals(TIME));
      assertFalse(MASS.equals(ELECTRIC_CURRENT));
      assertFalse(MASS.equals(LENGTH));
      assertFalse(MASS.equals(TEMPERATURE));
      assertFalse(MASS.equals(AMOUNT_OF_SUBSTANCE));
      assertFalse(MASS.equals(LUMINOUS_INTENSITY));
      assertEquals("[M]", MASS.toString());
   }

   @Test
   public void testTemperature() {
      assertEquals(TEMPERATURE, TEMPERATURE);
      assertFalse(TEMPERATURE.equals(NONE));
      assertFalse(TEMPERATURE.equals(TIME));
      assertFalse(TEMPERATURE.equals(ELECTRIC_CURRENT));
      assertFalse(TEMPERATURE.equals(LENGTH));
      assertFalse(TEMPERATURE.equals(MASS));
      assertFalse(TEMPERATURE.equals(AMOUNT_OF_SUBSTANCE));
      assertFalse(TEMPERATURE.equals(LUMINOUS_INTENSITY));
      assertEquals("[Θ]", TEMPERATURE.toString());
   }

   @Test
   public void testAmountOfSubstance() {
      assertEquals(AMOUNT_OF_SUBSTANCE, AMOUNT_OF_SUBSTANCE);
      assertFalse(AMOUNT_OF_SUBSTANCE.equals(NONE));
      assertFalse(AMOUNT_OF_SUBSTANCE.equals(TIME));
      assertFalse(AMOUNT_OF_SUBSTANCE.equals(ELECTRIC_CURRENT));
      assertFalse(AMOUNT_OF_SUBSTANCE.equals(LENGTH));
      assertFalse(AMOUNT_OF_SUBSTANCE.equals(MASS));
      assertFalse(AMOUNT_OF_SUBSTANCE.equals(TEMPERATURE));
      assertFalse(AMOUNT_OF_SUBSTANCE.equals(LUMINOUS_INTENSITY));
      assertEquals("[N]", AMOUNT_OF_SUBSTANCE.toString());
   }

   @Test
   public void testLuminousIntensity() {
      assertEquals(LUMINOUS_INTENSITY, LUMINOUS_INTENSITY);
      assertFalse(LUMINOUS_INTENSITY.equals(NONE));
      assertFalse(LUMINOUS_INTENSITY.equals(TIME));
      assertFalse(LUMINOUS_INTENSITY.equals(ELECTRIC_CURRENT));
      assertFalse(LUMINOUS_INTENSITY.equals(LENGTH));
      assertFalse(LUMINOUS_INTENSITY.equals(MASS));
      assertFalse(LUMINOUS_INTENSITY.equals(TEMPERATURE));
      assertFalse(LUMINOUS_INTENSITY.equals(AMOUNT_OF_SUBSTANCE));
      assertEquals("[J]", LUMINOUS_INTENSITY.toString());
   }

}