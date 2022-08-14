package com.futurestats.astronomy.algos.meeus;

import com.futurestats.astronomy.algos.meeus.Structs.Nutation;

public class Chapter22 {

	/**
	 * Calculates the mean obliquity of the ecliptic on the given Julian
	 * Ephemeris Day.
	 */
	public static double meanObliquityOfEcliptic(double jde) {
		double t = (jde - 2451545) / 36525;
		
		//try to use better equation (22.3):
		double u = t / 100;
		
		if (Math.abs(u) < 1) {
			double u2 = u * u;
			double u4 = u2 * u2;
			double u6 = u4 * u2;
			double u8 = u4 * u4;
			
			double seconds = (23 * 3600) + (26 * 60) + (21.448)
					- 4680.93 * u - 1.55 * u2 + 1999.25 * u2*u
					- 51.38 * u4 - 249.67 * u4 * u - 39.05 * u4 * u2
					+ 7.12 * u6 * u + 27.87 *u8 + 5.79 * u8*u + 2.45*u8*u2;
			
			return seconds / 3600;
		}
		
		//fall back to default (equation 22.2)
		double t2 = t * t;
		double t3 = t2 * t;
		return Utils.dmsToDegrees(23, 26, 21.448) 
				- Utils.dmsToDegrees(0, 0, 46.8150) * t
				- Utils.dmsToDegrees(0, 0, 0.00059) * t2
				+ Utils.dmsToDegrees(0, 0, 0.001813) * t3;
	}
	
	/**
	 * Calculations nutation using the approximate equation from Chapter 22
	 * which is accurate to within 0.5 seconds in psi and 0.1 seconds in epsilon.
	 */
	public static Nutation nutationApprox(double jde) {
		double t = (jde - 2451545) / 36525;
		double t2 = t*t;
		double t3 = t2 * t;
		
		double omega = 125.04452 - 1934.136261*t + 0.0020708*t2 + t3/450000;
		
		double L = 280.4665 + 36000.7698*t;
		double Lp = 218.3165 + 481267.8813*t;
		
		double nu_psi = -17.2 * TrigDegrees.sin(omega) 
				- 1.32 * TrigDegrees.sin(2*L) 	//book shows '- -' but a single '-' makes more sense
				- 0.23 * TrigDegrees.sin(2*Lp) 
				+ 0.21*TrigDegrees.sin(2*omega);
		
		double nu_eps = 9.20 * TrigDegrees.cos(omega)
				+ 0.57 * TrigDegrees.cos(2*L)
				+ 0.10 * TrigDegrees.cos(2*Lp)
				- 0.09 * TrigDegrees.cos(2*omega);
		
		return new Nutation(nu_psi, nu_eps);
	}
	
	/**
	 * Calculates the nutation of the ecliptic.
	 * 
	 * TODO: implement the better equation (the crazy long one).  Right now
	 * we just use the approximate one.
	 */
	public static Nutation nutation(double jde) {
		return nutationApprox(jde);
		
		/*
		//TODO: crazy long formula
		double t = (jde - 2451545) / 36525;
		double t2 = t*t;
		double t3 = t2 * t;
		
		double D = 297.85036 + 445267.111480*t - 0.0019142*t2 + t3/189474;
		double M = 357.52772 + 35999.050340*t - 0.0001603*t2 - t3/300000;
		double Mp = 134.96298 + 477198.867398*t + 0.0086972*t2 + t3/56250;
		double F = 93.27191 + 483202.017538*5 - 0.0036825*t2 + t3/327270;
		double omega = 125.04452 - 1934.136261*t + 0.0020708*t2 + t3/450000;
		 */
	}
	
	
}
