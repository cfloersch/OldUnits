package xpertss.measure.unit;


import org.junit.Test;
import xpertss.measure.Measurable;
import xpertss.measure.Measure;
import xpertss.measure.quantity.Pressure;

import static junit.framework.Assert.assertEquals;
import static xpertss.measure.unit.NonSI.*;

import java.math.BigDecimal;

public class NonSITest {


   @Test
   public void testPsi() {
      Measurable<Pressure> psi = Measure.valueOf(BigDecimal.ONE, POUND_PER_SQUARE_INCH);
      assertEquals(new BigDecimal("6894.8"), psi.getValue(SI.PASCAL));
      assertEquals(new BigDecimal(".068948"), psi.getValue(BAR));
   }

}