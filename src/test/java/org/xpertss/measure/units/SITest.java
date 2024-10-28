package org.xpertss.measure.units;

import org.junit.Test;
import xpertss.unit.types.AlternateUnit;
import xpertss.unit.types.BaseUnit;

import org.xpertss.measure.Dimension;
import org.xpertss.measure.Unit;
import org.xpertss.measure.UnitConverter;

import static org.junit.Assert.*;
import static org.xpertss.measure.units.SI.*;

public class SITest {

   @Test
   public void testBaseUnits() {
      testBaseUnit(AMPERE);
      assertSame(Dimension.ELECTRIC_CURRENT, AMPERE.getDimension());
      assertEquals("A", AMPERE.getSymbol());
      assertEquals("A", AMPERE.toString());
      assertFalse(AMPERE.isCompatible(METRE));

      testBaseUnit(CANDELA);
      assertSame(Dimension.LUMINOUS_INTENSITY, CANDELA.getDimension());
      assertEquals("cd", CANDELA.getSymbol());
      assertEquals("cd", CANDELA.toString());
      assertFalse(CANDELA.isCompatible(METRE));

      testBaseUnit(KELVIN);
      assertSame(Dimension.TEMPERATURE, KELVIN.getDimension());
      assertEquals("K", KELVIN.getSymbol());
      assertEquals("K", KELVIN.toString());
      assertFalse(KELVIN.isCompatible(METRE));

      testBaseUnit(KILOGRAM);
      assertSame(Dimension.MASS, KILOGRAM.getDimension());
      assertEquals("kg", KILOGRAM.getSymbol());
      assertEquals("kg", KILOGRAM.toString());
      assertFalse(KILOGRAM.isCompatible(METRE));

      testBaseUnit(METRE);
      assertSame(Dimension.LENGTH, METRE.getDimension());
      assertEquals("m", METRE.getSymbol());
      assertEquals("m", METRE.toString());
      assertFalse(METRE.isCompatible(CANDELA));

      testBaseUnit(MOLE);
      assertSame(Dimension.AMOUNT_OF_SUBSTANCE, MOLE.getDimension());
      assertEquals("mol", MOLE.getSymbol());
      assertEquals("mol", MOLE.toString());
      assertFalse(MOLE.isCompatible(METRE));

      testBaseUnit(SECOND);
      assertSame(Dimension.TIME, SECOND.getDimension());
      assertEquals("s", SECOND.getSymbol());
      assertEquals("s", SECOND.toString());
      assertFalse(SECOND.isCompatible(METRE));
   }

   public void testBaseUnit(Unit<?> unitToTest) {
      assertSame(BaseUnit.class, unitToTest.getClass());
      assertTrue(unitToTest.isSystemUnit());
      assertSame(UnitConverter.IDENTITY, unitToTest.toSystemUnit());
   }


   @Test
   public void testAlternateUnits() {
      testAlternateUnit(AMPERE_TURN);
      assertSame(Dimension.ELECTRIC_CURRENT, AMPERE_TURN.getDimension());
      assertEquals("At", AMPERE_TURN.getSymbol());
      assertEquals("At", AMPERE_TURN.toString());
      assertFalse(AMPERE_TURN.isCompatible(METRE));

      testAlternateUnit(RADIAN);
      assertSame(Dimension.NONE, RADIAN.getDimension());
      assertEquals("rad", RADIAN.getSymbol());
      assertEquals("rad", RADIAN.toString());
      assertFalse(RADIAN.isCompatible(METRE));

      testAlternateUnit(STERADIAN);
      assertSame(Dimension.NONE, STERADIAN.getDimension());
      assertEquals("sr", STERADIAN.getSymbol());
      assertEquals("sr", STERADIAN.toString());
      assertFalse(STERADIAN.isCompatible(METRE));

      testAlternateUnit(BIT);
      assertSame(Dimension.NONE, BIT.getDimension());
      assertEquals("bit", BIT.getSymbol());
      assertEquals("bit", BIT.toString());
      assertFalse(BIT.isCompatible(METRE));


      Dimension hertz = Dimension.NONE.divide(Dimension.TIME);

      testAlternateUnit(HERTZ);
      assertEquals(hertz, HERTZ.getDimension());
      assertEquals("Hz", HERTZ.getSymbol());
      assertEquals("Hz", HERTZ.toString());
      assertFalse(HERTZ.isCompatible(METRE));


      Dimension newton = Dimension.LENGTH.multiply(Dimension.MASS).divide(Dimension.TIME.pow(2));

      testAlternateUnit(NEWTON);
      assertEquals(newton, NEWTON.getDimension());
      assertEquals("N", NEWTON.getSymbol());
      assertEquals("N", NEWTON.toString());
      assertFalse(NEWTON.isCompatible(METRE));


      Dimension pascal = newton.divide(Dimension.LENGTH.pow(2));

      testAlternateUnit(PASCAL);
      assertEquals(pascal, PASCAL.getDimension());
      assertEquals("Pa", PASCAL.getSymbol());
      assertEquals("Pa", PASCAL.toString());
      assertFalse(PASCAL.isCompatible(METRE));


      Dimension joule = newton.multiply(Dimension.LENGTH);

      testAlternateUnit(JOULE);
      assertEquals(joule, JOULE.getDimension());
      assertEquals("J", JOULE.getSymbol());
      assertEquals("J", JOULE.toString());
      assertFalse(JOULE.isCompatible(METRE));


      Dimension watt = joule.divide(Dimension.TIME);

      testAlternateUnit(WATT);
      assertEquals(watt, WATT.getDimension());
      assertEquals("W", WATT.getSymbol());
      assertEquals("W", WATT.toString());
      assertFalse(WATT.isCompatible(METRE));


      Dimension coulomb = Dimension.TIME.multiply(Dimension.ELECTRIC_CURRENT);

      testAlternateUnit(COULOMB);
      assertEquals(coulomb, COULOMB.getDimension());
      assertEquals("C", COULOMB.getSymbol());
      assertEquals("C", COULOMB.toString());
      assertFalse(COULOMB.isCompatible(METRE));


      Dimension volt = watt.divide(Dimension.ELECTRIC_CURRENT);

      testAlternateUnit(VOLT);
      assertEquals(volt, VOLT.getDimension());
      assertEquals("V", VOLT.getSymbol());
      assertEquals("V", VOLT.toString());
      assertFalse(VOLT.isCompatible(METRE));


      Dimension farad = coulomb.divide(volt);

      testAlternateUnit(FARAD);
      assertEquals(farad, FARAD.getDimension());
      assertEquals("F", FARAD.getSymbol());
      assertEquals("F", FARAD.toString());
      assertFalse(FARAD.isCompatible(METRE));


      Dimension ohm = volt.divide(Dimension.ELECTRIC_CURRENT);

      testAlternateUnit(OHM);
      assertEquals(ohm, OHM.getDimension());
      assertEquals("Ω", OHM.getSymbol());
      assertEquals("Ω", OHM.toString());
      assertFalse(OHM.isCompatible(METRE));


      Dimension siemens = Dimension.ELECTRIC_CURRENT.divide(volt);

      testAlternateUnit(SIEMENS);
      assertEquals(siemens, SIEMENS.getDimension());
      assertEquals("S", SIEMENS.getSymbol());
      assertEquals("S", SIEMENS.toString());
      assertFalse(SIEMENS.isCompatible(METRE));


      Dimension weber = volt.multiply(Dimension.TIME);

      testAlternateUnit(WEBER);
      assertEquals(weber, WEBER.getDimension());
      assertEquals("Wb", WEBER.getSymbol());
      assertEquals("Wb", WEBER.toString());
      assertFalse(WEBER.isCompatible(METRE));


      Dimension tesla = weber.divide(Dimension.LENGTH.pow(2));

      testAlternateUnit(TESLA);
      assertEquals(tesla, TESLA.getDimension());
      assertEquals("T", TESLA.getSymbol());
      assertEquals("T", TESLA.toString());
      assertFalse(TESLA.isCompatible(METRE));


      Dimension henry = weber.divide(Dimension.ELECTRIC_CURRENT);

      testAlternateUnit(HENRY);
      assertEquals(henry, HENRY.getDimension());
      assertEquals("H", HENRY.getSymbol());
      assertEquals("H", HENRY.toString());
      assertFalse(HENRY.isCompatible(METRE));


      Dimension lumen = Dimension.LUMINOUS_INTENSITY.multiply(Dimension.NONE);

      testAlternateUnit(LUMEN);
      assertEquals(lumen, LUMEN.getDimension());
      assertEquals("lm", LUMEN.getSymbol());
      assertEquals("lm", LUMEN.toString());
      assertFalse(LUMEN.isCompatible(METRE));


      Dimension lux = lumen.divide(Dimension.LENGTH.pow(2));

      testAlternateUnit(LUX);
      assertEquals(lux, LUX.getDimension());
      assertEquals("lx", LUX.getSymbol());
      assertEquals("lx", LUX.toString());
      assertFalse(LUX.isCompatible(METRE));


      Dimension becquerel = Dimension.NONE.divide(Dimension.TIME);

      testAlternateUnit(BECQUEREL);
      assertEquals(becquerel, BECQUEREL.getDimension());
      assertEquals("Bq", BECQUEREL.getSymbol());
      assertEquals("Bq", BECQUEREL.toString());
      assertFalse(BECQUEREL.isCompatible(METRE));


      Dimension gray = joule.divide(Dimension.MASS);

      testAlternateUnit(GRAY);
      assertEquals(gray, GRAY.getDimension());
      assertEquals("Gy", GRAY.getSymbol());
      assertEquals("Gy", GRAY.toString());
      assertFalse(GRAY.isCompatible(METRE));


      Dimension sievert = joule.divide(Dimension.MASS);

      testAlternateUnit(SIEVERT);
      assertEquals(sievert, SIEVERT.getDimension());
      assertEquals("Sv", SIEVERT.getSymbol());
      assertEquals("Sv", SIEVERT.toString());
      assertFalse(SIEVERT.isCompatible(METRE));


      Dimension katal = Dimension.AMOUNT_OF_SUBSTANCE.divide(Dimension.TIME);

      testAlternateUnit(KATAL);
      assertEquals(katal, KATAL.getDimension());
      assertEquals("kat", KATAL.getSymbol());
      assertEquals("kat", KATAL.toString());
      assertFalse(KATAL.isCompatible(METRE));

   }

   public void testAlternateUnit(Unit<?> unitToTest) {
      assertSame(AlternateUnit.class, unitToTest.getClass());
      assertTrue(unitToTest.isSystemUnit());
      assertSame(UnitConverter.IDENTITY, unitToTest.toSystemUnit());
   }

}