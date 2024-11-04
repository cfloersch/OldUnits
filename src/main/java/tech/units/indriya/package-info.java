/*
 * Units of Measurement Reference Implementation
 * Copyright (c) 2005-2023, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-385, Indriya nor the names of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/**
 * This package provides the Reference Implementation of the
 * <a href="http://unitsofmeasurement.github.io/">Units of Measurement API</a>.
 *
 *
 * <h3>Usage:</h3>
 * {@code
 *
 * import javax.measure.quantity.*; // Holds quantity types.
 * import static javax.measure.MetricPrefix.*;
 * 
 * import tech.units.indriya.AbstractUnit;
 * import tech.units.indriya.function.AbstractConverter;
 * 
 * import static tech.units.indriya.unit.Units.*; // Standard CommonUnits.
 * import static system.uom.common.USCustomary.MILE; // Non-standard unit.
 * 
 * public class Main {
 *     public void main(String[] args) {
 *
 *         // Conversion between units (explicit way).
 *         AbstractUnit<Length> sourceUnit = KILO(METRE);
 *         AbstractUnit<Length> targetUnit = MILE;
 *         UnitConverter uc = sourceUnit.getConverterTo(targetUnit);
 *         System.out.println(uc.convert(10)); // Converts 10 km to miles.
 *
 *         // Same conversion than above, packed in one line.
 *         System.out.println(KILO(METRE).getConverterTo(MILE).convert(10));
 *
 *         // Retrieval of the SI unit (identifies the measurement type).
 *         System.out.println(REVOLUTION.divide(MINUTE).toSI());
 *
 *         // Dimension checking (allows/disallows conversions)
 *         System.out.println(ELECTRON_VOLT.isCompatible(WATT.times(HOUR)));
 *
 *         // Retrieval of the unit dimension (depends upon the current model).
 *         System.out.println(ELECTRON_VOLT.getDimension());
 *     }
 * }
 *
 * &gt; 6.2137119223733395
 * &gt; 6.2137119223733395
 * &gt; rad/s
 * &gt; true
 * &gt; [L]²·[M]/[T]²
 * }
 *
 * <h3>Unit Parameterization</h3>
 *
 *     CommonUnits are parameterized enforce compile-time checks of units/measures consistency, for example:
 * {@code
 *     Unit<Time> MINUTE = SECOND.multiply(60); // Ok.
 *     Unit<Time> MINUTE = METRE.multiply(60); // Compile error.
 *
 *     Unit<Pressure> HECTOPASCAL = HECTO(PASCAL); // Ok.
 *     Unit<Pressure> HECTOPASCAL = HECTO(NEWTON); // Compile error.
 *
 *     Quantity<Time> duration = Quantities.getQuantity(2, MINUTE); // Ok.
 *     Quantity<Time> duration = Quantities.getQuantity(2, CELSIUS); // Compile error.
 *
 *  }
 *
 *     Runtime checks of dimension consistency can be done for more complex cases.
 *
 *  {@code
 *     Unit<Area> SQUARE_METRE = METRE.times(METRE).asType(Area.class); // Ok.
 *     Unit<Area> SQUARE_METRE = METRE.times(KELVIN).asType(Area.class); // Runtime error.
 *
 *     Unit<Temperature> KELVIN = AbstractUnit.parse("K").asType(Temperature.class); // Ok.
 *     Unit<Temperature> KELVIN = AbstractUnit.parse("kg").asType(Temperature.class); // Runtime error.
 *  }
 *
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:werner@units.tech">Werner Keil</a>
 * @version 2.2
 * @since 1.0
 */
package tech.units.indriya;
