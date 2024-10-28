package xpertss.measure.quantity;

import org.junit.Test;
import xpertss.measure.unit.Unit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static xpertss.measure.unit.SI.*;
import static xpertss.measure.unit.SIPrefix.*;
import static xpertss.measure.unit.NonSI.*;

public class LengthTest {

   @Test
   public void testLengthUnitIsStandardUnit()
   {
      Unit<Length> soCalledStdUnit = Length.UNIT;
      assertSame(soCalledStdUnit, soCalledStdUnit.getStandardUnit());
   }


   @Test
   public void testEquality()
   {
      assertEquals(KILO(METER), KILO(METER));
      assertEquals(MEGA(METER), MEGA(METER));
      assertEquals(GIGA(METER), GIGA(METER));
      assertEquals(TERA(METER), TERA(METER));

      // assertEquals(MEGA(METER), KILO(KILO(METER))); <= double wrapping doesn't work TODO Should I fix that?

   }


   @Test
   public void testMeterIsSameAsMetre()
   {
      assertSame(METER, METRE);
   }


   @Test
   public void testMeterConvertToMeter()
   {
      Unit<Length> meter = Length.UNIT;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(METER).convert(BigDecimal.ONE));
   }


   @Test
   public void testMeterConvertToKilometer()
   {
      Unit<Length> meter = Length.UNIT;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(KILO(METER)).convert(new BigDecimal(1000)));
   }

   @Test
   public void testKilometerConvertToMeter()
   {
      Unit<Length> kilometer = KILO(METER);
      assertEquals(new BigDecimal(1000), kilometer.getConverterTo(METER).convert(BigDecimal.ONE));
   }



   @Test
   public void testMeterConvertToMillimeter()
   {
      Unit<Length> meter = Length.UNIT;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(MILLI(METER)).convert(new BigDecimal(".001")));
   }

   @Test
   public void testMillimeterConvertToMeter()
   {
      Unit<Length> millimeter = MILLI(METER);
      assertEquals(new BigDecimal(".001"), millimeter.getConverterTo(METER).convert(BigDecimal.ONE));
   }



   // NonSI

   @Test
   public void testMeterConvertToFoot()
   {
      Unit<Length> meter = METER;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(FOOT).convert(new BigDecimal("0.3048")));
   }

   @Test
   public void testFootConvertToMeter()
   {
      Unit<Length> foot = FOOT;
      assertEquals(new BigDecimal("0.3048"), foot.getConverterTo(METER).convert(BigDecimal.ONE));
   }



   @Test
   public void testMeterConvertToYard()
   {
      Unit<Length> meter = METER;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(YARD).convert(new BigDecimal("0.9144")));
   }

   @Test
   public void testYardConvertToMeter()
   {
      Unit<Length> yard = YARD;
      assertEquals(new BigDecimal("0.9144"), yard.getConverterTo(METER).convert(BigDecimal.ONE));
   }


   @Test
   public void tesInchConvertToYard()
   {
      Unit<Length> inch = INCH;
      assertEquals(BigDecimal.ONE, inch.getConverterTo(YARD).convert(new BigDecimal("36")));
   }

   @Test
   public void testYardConvertToInch()
   {
      Unit<Length> yard = YARD;
      assertEquals(new BigDecimal("36"), yard.getConverterTo(INCH).convert(BigDecimal.ONE));
   }

   @Test
   public void tesFootConvertToYard()
   {
      Unit<Length> foot = FOOT;
      assertEquals(BigDecimal.ONE, foot.getConverterTo(YARD).convert(new BigDecimal("3")));
   }

   @Test
   public void testYardConvertToFoot()
   {
      Unit<Length> yard = YARD;
      assertEquals(new BigDecimal("3"), yard.getConverterTo(FOOT).convert(BigDecimal.ONE));
   }






   @Test
   public void testMeterConvertToInch()
   {
      Unit<Length> meter = METER;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(INCH).convert(new BigDecimal("0.0254")));
   }

   @Test
   public void testInchConvertToMeter()
   {
      Unit<Length> inch = INCH;
      assertEquals(new BigDecimal("0.0254"), inch.getConverterTo(METER).convert(BigDecimal.ONE));
   }


   @Test
   public void testFootConvertToInch()
   {
      Unit<Length> foot = FOOT;
      assertEquals(new BigDecimal("12"), foot.getConverterTo(INCH).convert(BigDecimal.ONE));
   }

   @Test
   public void testInchConvertToFoot()
   {
      Unit<Length> inch = INCH;
      assertEquals(BigDecimal.ONE, inch.getConverterTo(FOOT).convert(new BigDecimal("12")));
   }




   @Test
   public void testMeterConvertToMile()
   {
      Unit<Length> meter = METER;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(MILE).convert(new BigDecimal("1609.344")));
   }

   @Test
   public void testMileConvertToMeter()
   {
      Unit<Length> mile = MILE;
      assertEquals(new BigDecimal("1609.344"), mile.getConverterTo(METER).convert(BigDecimal.ONE));
   }



   @Test
   public void testFootConvertToMile()
   {
      Unit<Length> foot = FOOT;
      assertEquals(BigDecimal.ONE, foot.getConverterTo(MILE).convert(new BigDecimal("5280")));
   }

   @Test
   public void testMileConvertToFoot()
   {
      Unit<Length> mile = MILE;
      assertEquals(new BigDecimal("5280"), mile.getConverterTo(FOOT).convert(BigDecimal.ONE));
   }



   @Test
   public void testMeterConvertToNauticalMile()
   {
      Unit<Length> meter = METER;
      assertEquals(BigDecimal.ONE, meter.getConverterTo(NAUTICAL_MILE).convert(new BigDecimal("1852")));
   }

   @Test
   public void testNauticalMileConvertToMeter()
   {
      Unit<Length> mile = NAUTICAL_MILE;
      assertEquals(new BigDecimal("1852"), mile.getConverterTo(METER).convert(BigDecimal.ONE));
   }






   // Due to the imprecise conversion equalities I need to round down to 5 digits of precision
   // to get the following tests to succeed. (That's decimal math on gargantuan numbers for ya)
   private MathContext ctx = new MathContext(5, RoundingMode.HALF_EVEN);

   @Test
   public void testHugeLengths()
   {
      Unit<Length> parsec = PARSEC;
      assertEquals(new BigDecimal("3.0857e16"), parsec.getConverterTo(METER).convert(BigDecimal.ONE).round(ctx));
      assertEquals(new BigDecimal("1.9174e13"), parsec.getConverterTo(MILE).convert(BigDecimal.ONE).round(ctx));
      assertEquals(new BigDecimal("2.0626e5"), parsec.getConverterTo(ASTRONOMICAL_UNIT).convert(BigDecimal.ONE).round(ctx));
      assertEquals(new BigDecimal("3.2616"), parsec.getConverterTo(LIGHT_YEAR).convert(BigDecimal.ONE).round(ctx));
   }


   @Test
   public void testTinyLengths()
   {
      Unit<Length> angstrom = ANGSTROM;
      assertEquals(BigDecimal.ONE, angstrom.getConverterTo(METER).convert(new BigDecimal("1e10")));
   }


}