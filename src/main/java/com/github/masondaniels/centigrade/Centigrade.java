package com.github.masondaniels.centigrade;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class Centigrade {

	public static void respondToMessage(Message message, String response) {
		final MessageChannel channel = message.getChannel().block();
		channel.createMessage(response).block();
	}
	
	private static final Conversion F_TO_C = new Conversion(ConversionType.FAHRENHEIT, ConversionType.CELSIUS) {

		@Override
		public double convert(double input) {
			return (input - 32d) * (5d / 9d);
		}
	};

	private static final  Conversion C_TO_F = new Conversion(ConversionType.CELSIUS, ConversionType.FAHRENHEIT) {

		@Override
		public double convert(double input) {
			return (input * (9d / 5d)) + 32;
		}
	};
	
	private static final Conversion CM_TO_INCH = new Conversion(ConversionType.CENTIMETER, ConversionType.INCH) {
		
		@Override
		public double convert(double input) {
			return input / 2.54;
		}
	};
	
	private static final Conversion INCH_TO_CM = new Conversion(ConversionType.INCH, ConversionType.CENTIMETER) {
		
		@Override
		public double convert(double input) {
			return input * 2.54d;
		}
	};
	
	private static final Conversion KM_TO_MI = new Conversion(ConversionType.KILOMETER, ConversionType.MILE) {
		
		@Override
		public double convert(double input) {
			return input / 1.609d;
		}
	};
	
	private static final Conversion MI_TO_KM = new Conversion(ConversionType.MILE, ConversionType.KILOMETER) {
		
		@Override
		public double convert(double input) {
			return input * 1.609d;
		}
	};
	
	private static final Conversion FT_TO_M = new Conversion(ConversionType.FEET, ConversionType.METER) {
		
		@Override
		public double convert(double input) {
			return input / 3.281d;
		}
	};
	
	private static final Conversion M_TO_FT = new Conversion(ConversionType.METER, ConversionType.FEET) {
		
		@Override
		public double convert(double input) {
			return input * 3.281d;
		}
	};
	
	private static final Conversion K_TO_C = new Conversion(ConversionType.KELVIN, ConversionType.CELSIUS) {
		
		@Override
		public double convert(double input) {
			return input - 273.15;
		}
	};
	
	private static final Conversion STONE_TO_KG = new Conversion(ConversionType.STONE, ConversionType.KILOGRAM) {
		
		@Override
		public double convert(double input) {
			return input * 6.35;
		}
	};
	
	private static final Conversion KG_TO_LBS = new Conversion(ConversionType.KILOGRAM, ConversionType.POUND) {
		
		@Override
		public double convert(double input) {
			return input * 2.205;
		}
	};
	
	private static final Conversion LBS_TO_KG = new Conversion(ConversionType.POUND, ConversionType.KILOGRAM) {
		
		@Override
		public double convert(double input) {
			return input / 2.205;
		}
	};
	
	

	public static void evaluate(Message message, String nickname) {
		String content = message.getContent();
		MessageProfile profile = new MessageProfile(content);
		
		F_TO_C.convertMessage(profile);
		C_TO_F.convertMessage(profile);
		CM_TO_INCH.convertMessage(profile);
		INCH_TO_CM.convertMessage(profile);
		KM_TO_MI.convertMessage(profile);
		MI_TO_KM.convertMessage(profile);
		M_TO_FT.convertMessage(profile);
		FT_TO_M.convertMessage(profile);
		K_TO_C.convertMessage(profile);
		STONE_TO_KG.convertMessage(profile);
		KG_TO_LBS.convertMessage(profile);
		LBS_TO_KG.convertMessage(profile);

		if (profile.isAltered()) {
			respondToMessage(message, "I think " + nickname + " meant to say: ```" + profile.getMessage() + "```");
		}

	}

}
