package org.xpertss.measure.quantity;

import org.junit.Test;

import org.xpertss.measure.units.SI;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MassTest {

   private static final BigDecimal ONE_THOUSAND_GRAMS = BigDecimal.valueOf(1000);

   @Test
   public void testGrams() {
      assertEquals(ONE_THOUSAND_GRAMS, SI.KILOGRAM.getConverterTo(SI.GRAM).convert(BigDecimal.ONE));
      assertEquals(BigDecimal.ONE, SI.GRAM.getConverterTo(SI.KILOGRAM).convert(ONE_THOUSAND_GRAMS));
   }

}