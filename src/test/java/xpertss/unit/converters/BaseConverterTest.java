package xpertss.unit.converters;

import org.junit.Before;
import org.junit.Test;

import org.xpertss.measure.UnitConverter;
import java.math.BigDecimal;


import static org.junit.Assert.*;

public class BaseConverterTest {

   private BaseConverter objectUnderTest;

   @Before
   public void setUp() {
      objectUnderTest = new MyBaseConverter();
   }


   @Test
   public void testCleanSimple() {
      assertEquals(BigDecimal.TEN, objectUnderTest.clean(new BigDecimal("10.00000000")));
      assertEquals(BigDecimal.TEN, objectUnderTest.clean(new BigDecimal("10.0")));
      assertEquals(BigDecimal.valueOf(12), objectUnderTest.clean(new BigDecimal("12")));
      assertEquals(BigDecimal.valueOf(1234567890), objectUnderTest.clean(new BigDecimal("1234567890")));
      assertEquals(BigDecimal.valueOf(12345678901234567L), objectUnderTest.clean(new BigDecimal("12345678901234567")));
      assertEquals(new BigDecimal(".1"), objectUnderTest.clean(new BigDecimal(".1")));
      assertEquals(new BigDecimal(".12"), objectUnderTest.clean(new BigDecimal(".12")));
      assertEquals(new BigDecimal(".123"), objectUnderTest.clean(new BigDecimal(".123")));
      assertEquals(new BigDecimal(".1234"), objectUnderTest.clean(new BigDecimal(".1234")));
      assertEquals(new BigDecimal(".12345"), objectUnderTest.clean(new BigDecimal(".12345")));
      assertEquals(new BigDecimal(".123456"), objectUnderTest.clean(new BigDecimal(".123456")));
      assertEquals(new BigDecimal(".1234567"), objectUnderTest.clean(new BigDecimal(".1234567")));
      assertEquals(new BigDecimal(".12345678"), objectUnderTest.clean(new BigDecimal(".12345678")));
      assertEquals(new BigDecimal("5.123456"), objectUnderTest.clean(new BigDecimal("5.123456")));
      assertEquals(new BigDecimal("5.000001"), objectUnderTest.clean(new BigDecimal("5.000001")));
      assertEquals(new BigDecimal("5.0000000001"), objectUnderTest.clean(new BigDecimal("5.0000000001")));
   }

   @Test
   public void testCleanAdvanced() {
      // DECIMAL128 has 34 significant digits
      assertEquals(BigDecimal.TEN, objectUnderTest.clean(new BigDecimal("1e1")));
      assertEquals(BigDecimal.valueOf(110), objectUnderTest.clean(new BigDecimal("11e1")));
      assertEquals(BigDecimal.valueOf(100), objectUnderTest.clean(new BigDecimal("1e2")));
      assertEquals(BigDecimal.valueOf(1100), objectUnderTest.clean(new BigDecimal("11e2")));
      assertEquals(BigDecimal.valueOf(1000), objectUnderTest.clean(new BigDecimal("1e3")));
      assertEquals(BigDecimal.valueOf(10000), objectUnderTest.clean(new BigDecimal("1e4")));
      assertEquals(BigDecimal.valueOf(100000), objectUnderTest.clean(new BigDecimal("1e5")));
      assertEquals(BigDecimal.valueOf(1000000), objectUnderTest.clean(new BigDecimal("1e6")));

      assertEquals(new BigDecimal("3.4E33"), objectUnderTest.clean(new BigDecimal("3.4E33")));
      assertEquals(new BigDecimal("3.4E8"), objectUnderTest.clean(new BigDecimal("3.4E8")));
      assertEquals(new BigDecimal("3.4E7"), objectUnderTest.clean(new BigDecimal("3.4E7")));
      assertEquals(new BigDecimal("34000"), objectUnderTest.clean(new BigDecimal("3.4E4")));
      assertEquals(new BigDecimal("3400000"), objectUnderTest.clean(new BigDecimal("3.4E6")));
      assertEquals(new BigDecimal("3417000"), objectUnderTest.clean(new BigDecimal("3.417E6")));
      assertEquals(new BigDecimal("3417281"), objectUnderTest.clean(new BigDecimal("3.417281E6")));
      assertEquals(new BigDecimal("3417281"), objectUnderTest.clean(new BigDecimal("341.7281E4")));
      assertEquals(new BigDecimal("3417280"), objectUnderTest.clean(new BigDecimal("341728E1")));
      assertEquals(new BigDecimal("3417281E1"), objectUnderTest.clean(new BigDecimal("3417281E1")));
      assertEquals(new BigDecimal("341.7281E5"), objectUnderTest.clean(new BigDecimal("341.7281E5")));
   }




   private static class MyBaseConverter extends BaseConverter {

      @Override
      public UnitConverter inverse()
      {
         return null;
      }

      @Override
      public BigDecimal convert(BigDecimal value)
      {
         return null;
      }
   }
}