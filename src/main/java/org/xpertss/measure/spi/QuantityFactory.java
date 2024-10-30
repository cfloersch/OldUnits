package org.xpertss.measure.spi;

import org.xpertss.measure.Quantity;
import org.xpertss.measure.Unit;

import java.math.BigDecimal;

/**
 * ServiceProvider provider = ServiceProvider.current();
 * QuantityFactory<Time> timeFactory = provider.getQuantityFactory(Time.class);
 * QuantityFactory<Length> lengthFactory = provider.getQuantityFactory(Length.class);
 * Quantity<Time> time = timeFactory.create(12, MILLI(SECOND));
 * Quantity<Length> length = lengthFactory.create(34.5, MILE);
 */
public interface QuantityFactory {


    public <Q extends Quantity<Q>> Quantity<Q> of(BigDecimal value, Unit<Q> unit);
}
