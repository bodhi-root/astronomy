package com.futurestats.astronomy.algos.meeus;

public class Chapter27 {

	public static enum Target {
		MARCH_EQUINOX, 
		JUNE_SOLSTICE, 
		SEPT_EQUINOX, 
		DEC_SOLSTICE
	};
	
	public static double jdeForEvent(int year, Target target) {
		
		double jde0 = jdeForMeanEvent(year, target);
		double t = (jde0 - 2451545.0) / 36525;
		double w = 35999.373*t - 2.47;
		double delta_lambda = 1 + 0.0334 * TrigDegrees.cos(w) + 0.0007 * TrigDegrees.cos(2*w);
		
		double s = 485 * TrigDegrees.cos(324.96 + (1934.136*t)) +
				   203 * TrigDegrees.cos(337.23 + (32964.467*t)) +
				   199 * TrigDegrees.cos(342.08 + (20.186*t)) +
				   182 * TrigDegrees.cos(27.85 + (445267.112*t)) +
				   156 * TrigDegrees.cos(73.14 + (45036.886*t)) +
				   136 * TrigDegrees.cos(171.52 + (22518.443*t)) +
				   77 * TrigDegrees.cos(222.54 + (65928.934*t)) +
				   74 * TrigDegrees.cos(296.72 + (3034.906*t)) +
				   70 * TrigDegrees.cos(243.58 + (9037.513*t)) +
				   58 * TrigDegrees.cos(119.81 + (33718.147*t)) +
				   52 * TrigDegrees.cos(297.17 + (150.678*t)) +
				   50 * TrigDegrees.cos(21.02 + (2281.226*t)) +
				   45 * TrigDegrees.cos(247.54 + (29939.562*t)) +
				   44 * TrigDegrees.cos(325.15 + (31555.956*t)) +
				   29 * TrigDegrees.cos(60.93 + (4443.417*t)) +
				   18 * TrigDegrees.cos(155.12 + (67555.328*t)) +
				   17 * TrigDegrees.cos(288.79 + (4562.452*t)) +
				   16 * TrigDegrees.cos(198.04 + (62894.029*t)) +
				   14 * TrigDegrees.cos(199.76 + (31436.921*t)) +
				   12 * TrigDegrees.cos(95.39 + (14577.848*t)) +
				   12 * TrigDegrees.cos(287.11 + (31931.756*t)) +
				   12 * TrigDegrees.cos(320.81 + (34777.259*t)) +
				   9 * TrigDegrees.cos(227.73 + (1222.114*t)) +
				   8 * TrigDegrees.cos(15.45 + (16859.074*t));
		
		double jde = jde0 + 0.00001 * s / delta_lambda;	   
		return jde;
		
	}
	
	public static double jdeForMeanEvent(int year, Target target) {
		
		if (year >= -1000 && year <= 1000) {
			double y = year / 1000.0;
			
			double y2 = y * y;
			double y3 = y2 * y;
			double y4 = y3 * y;
			
			switch(target) {
			case MARCH_EQUINOX:
				return 1721139.29189 + 365242.13740*y + 0.06134*y2 + 0.00111*y3 - 0.00071*y4;
			case JUNE_SOLSTICE:
				return 1721233.25401 + 365241.72562*y - 0.05323*y2 + 0.00907*y3 + 0.00025*y4;
			case SEPT_EQUINOX:
				return 1721325.70455 + 365242.49558*y - 0.11677*y2 - 0.00297*y3 + 0.00074*y4;
			case DEC_SOLSTICE:
				return 1721414.39987 + 365242.88257*y - 0.00769*y2 - 0.00933*y3 - 0.00006*y4;
			}
			
		}
		else if (year >= 1000 && year <= 3000) {
			double y = (year - 2000) / 1000.0;
			
			double y2 = y * y;
			double y3 = y2 * y;
			double y4 = y3 * y;
			
			switch(target) {
			case MARCH_EQUINOX:
				return 2451623.80984 + 365242.37404*y + 0.05169*y2 - 0.00411*y3 - 0.00057*y4;
			case JUNE_SOLSTICE:
				return 2451716.56767 + 365241.62603*y + 0.00325*y2 - 0.00888*y3 - 0.00030*y4;
			case SEPT_EQUINOX:
				return 2451810.21715 + 365242.01767*y - 0.11575*y2 + 0.00337*y3 + 0.00078*y4;
			case DEC_SOLSTICE:
				return 2451900.05952 + 365242.74049*y - 0.06223*y2 - 0.00823*y3 + 0.00032*y4;
			}
			
		}
		
		throw new IllegalArgumentException("Unsupported year: " + year);	
	}
	
}
