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
package tech.units.indriya.format;

import static tech.units.indriya.format.FormatBehavior.LOCALE_SENSITIVE;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.measure.format.QuantityFormat;
import javax.measure.format.UnitFormat;
import javax.measure.spi.FormatService;

import tech.units.indriya.format.SimpleUnitFormat.Flavor;
import tech.uom.lib.common.function.IntPrioritySupplier;

/**
 * Default format service.
 *
 * @author Werner Keil
 * @version 2.3, November 26, 2020
 * @since 2.0
 */
public class DefaultFormatService implements FormatService, IntPrioritySupplier {
	private static final int PRIO = 1000;

	private static final String SIMPLE_KEY = "SIMPLE";
	private static final String EBNF_KEY = "EBNF";
	private static final String LOCAL_KEY = "LOCAL";
	
	private static final String DEFAULT_QUANTITY_FORMAT_NAME = SIMPLE_KEY;

	private static final String DEFAULT_UNIT_FORMAT_NAME = SIMPLE_KEY + "_DEFAULT";
	private static final String ASCII_UNIT_FORMAT_NAME = SIMPLE_KEY + "_ASCII";
	
	private final Map<String, QuantityFormat> quantityFormats = new HashMap<>();
	private final Map<String, UnitFormat> unitFormats = new HashMap<>();

	private final Map<String, String> unitFormatAliases = new HashMap<>();
	private final Map<String, String> quantityFormatAliases = new HashMap<>();

	/**
	 * Holds the default format instance (EBNFUnitFormat).
	 */
	private static final NumberDelimiterQuantityFormat EBNF_QUANTITY_FORMAT = new NumberDelimiterQuantityFormat.Builder()
			.setNumberFormat(NumberFormat.getInstance(Locale.ROOT)).setUnitFormat(EBNFUnitFormat.getInstance()).build();

	public DefaultFormatService() {
		unitFormats.put(DEFAULT_UNIT_FORMAT_NAME, SimpleUnitFormat.getInstance());
		unitFormats.put(ASCII_UNIT_FORMAT_NAME, SimpleUnitFormat.getInstance(Flavor.ASCII));
		unitFormats.put(EBNF_KEY, EBNFUnitFormat.getInstance());
		unitFormats.put(LOCAL_KEY, LocalUnitFormat.getInstance());

		unitFormatAliases.put("DEFAULT", DEFAULT_UNIT_FORMAT_NAME);
		unitFormatAliases.put("ASCII", ASCII_UNIT_FORMAT_NAME);
		
		quantityFormats.put(DEFAULT_QUANTITY_FORMAT_NAME, SimpleQuantityFormat.getInstance());
		quantityFormats.put("NUMBERDELIMITER", NumberDelimiterQuantityFormat.getInstance());
		quantityFormats.put(EBNF_KEY, EBNF_QUANTITY_FORMAT);
		quantityFormats.put(LOCAL_KEY, NumberDelimiterQuantityFormat.getInstance(LOCALE_SENSITIVE));

		quantityFormatAliases.put("NUMBERSPACE", "NUMBERDELIMITER");
	}

	@Override
	public QuantityFormat getQuantityFormat(String key) {
		Objects.requireNonNull(key, "Format name or alias required");
		String alias = quantityFormatAliases.get(key.toUpperCase());
		if (alias != null && alias.length() > 0) {
			return quantityFormats.get(alias.toUpperCase());
		}
		return quantityFormats.get(key.toUpperCase());
	}

	@Override
	public QuantityFormat getQuantityFormat() {
		return getQuantityFormat(DEFAULT_QUANTITY_FORMAT_NAME);
	}

	@Override
	public Set<String> getAvailableFormatNames(FormatType type) {
		switch (type) {
		case QUANTITY_FORMAT:
			return quantityFormats.keySet();
		default:
			return unitFormats.keySet();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UnitFormatService#getUnitFormat(String)
	 */
	@Override
	public UnitFormat getUnitFormat(String key) {
		Objects.requireNonNull(key, "Format name or alias required");
		String alias = unitFormatAliases.get(key.toUpperCase());
		if (alias != null && alias.length() > 0) {
			return unitFormats.get(alias);
		}
		return unitFormats.get(key.toUpperCase());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UnitFormatService#getUnitFormat()
	 */
	@Override
	public UnitFormat getUnitFormat() {
		return getUnitFormat(DEFAULT_UNIT_FORMAT_NAME);
	}

	@Override
	public UnitFormat getUnitFormat(String name, String variant) {
		final StringBuilder sb = new StringBuilder(name);
		if (null != variant && !variant.isEmpty()) {
			sb.append("_");
			sb.append(variant);
		}
		return getUnitFormat(sb.toString());
	}

	@Override
	public int getPriority() {
		return PRIO;
	}
}
