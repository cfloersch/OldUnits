package org.xpertss.measure.spi;

import org.xpertss.measure.format.QuantityFormat;
import org.xpertss.measure.format.UnitFormat;

/**
 * ServiceProvider provider = ServiceProvider.current();
 *
 * FormatService formatService = provider.getFormatService();
 * UnitFormat format = formatService.getUnitFormat();
 * Unit<Length> metre = format.parse("m").asType(Length.class);
 * Unit<Force> newton = format.parse("m.kg/s2").asType(Force.class);
 * Unit<Volume> m3 = format.parse("m³").asType(Volume.class);
 */
public interface FormatService {

    public UnitFormat getUnitFormat();

    public QuantityFormat getQuantityFormat();

}
