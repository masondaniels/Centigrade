package com.github.masondaniels.centigrade;

import java.text.DecimalFormat;
import java.util.regex.Matcher;

public abstract class Conversion {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	private ConversionType typeIn;
	private ConversionType typeOut;

	public Conversion(ConversionType typeIn, ConversionType typeOut) {
		this.typeIn = typeIn;
		this.typeOut = typeOut;
	}

	
	
	public void convertMessage(MessageProfile mp) {
		String input = mp.getInput();
		if (!((input.contains("0") || input.contains("1") || input.contains("2") || input.contains("3")
				|| input.contains("4") || input.contains("5") || input.contains("6") || input.contains("7")
				|| input.contains("8") || input.contains("9")))) {
			return;
		}
		Matcher matcher = typeIn.getPattern().matcher(input);
		while (matcher.find()) {
			double toBeConverted = Double.parseDouble(matcher.group(1));
			double converted = convert(toBeConverted);
			mp.add(matcher.group(), df.format(converted) + " " + typeOut.getSymbol());
		}
	}

	public abstract double convert(double input); // in to out
}
