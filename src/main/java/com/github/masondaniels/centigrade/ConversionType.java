package com.github.masondaniels.centigrade;

import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;

public enum ConversionType {
	FAHRENHEIT("fahrenheit", "°F", "f"), CELSIUS("celsius", "°C", "c"), CENTIMETER("centimeter", "cm", "centimeters"),
	METER("meter", "m", "meters"), INCH("inch", "in", "inches"), MILE("mile", "mi", "miles"),
	KILOMETER("kilometer", "km", "kilometers"), FEET("feet", "ft", "foot"), KELVIN("kelvin", "K", "kelvins"),
	POUND("pound", "lbs", "pounds", "lb"), KILOGRAM("kilogram", "kg", "kgs", "kilograms"), STONE("stone", "stone", "stones");

	private Pattern pattern;
	private String symbol;
	private String name;
	private List<String> keywords = new ArrayList<String>();

	private ConversionType(String name, String symbol, String... keywords) {
		this.name = name;
		this.keywords.add(name);
		this.symbol = symbol;
		StringBuilder sb = new StringBuilder();
		sb.append("(-?\\d+(?:\\.\\d+)?)\\s*((?i)" + name + "|(?i)" + keywords[0] + "|(?i)" + symbol);
		for (int i = 1; i < keywords.length; i++) {
			this.keywords.add(keywords[i]);

			sb.append("|(?i)" + keywords[i]);
		}

		sb.append(")\\b");
		pattern = Pattern.compile(sb.toString());

	}

	public String getName() {
		return name;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public String getSymbol() {
		return symbol;
	}
}
