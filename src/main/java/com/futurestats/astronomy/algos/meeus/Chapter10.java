package com.futurestats.astronomy.algos.meeus;

/**
 * Chapter 10: Dynamic and Universal Time
 * 
 * Universal Time (UT) is the local time at Greenwich (also called Greenwich
 * Civil Time).  This is erroneously called Greenwich Mean Time (GMT) by
 * many, but GMT is defined by astronomers to begin at noon.  This means
 * that 00:00 GMT is actually 12:00 UT at Greenwich.
 * 
 * UT is not a "uniform time" though, since the Earth's rotational speed
 * changes slightly (and unpredictably).  Dynamical Time is used by
 * astronomers to provide a uniform time.  There are two versions of 
 * Dynamical Time:
 * 
 * Barycentric Dynamical Time (BDT)
 * Terrestrial Dynamical Time (TBT), also called Terrestrial Time (TT)
 * 
 * These differ by at most 0.0017 seconds.  A.A. makes no distinction 
 * between these two and refers to them all as Dynamic Time (TD).
 */
public class Chapter10 {

	static final double [] LOOKUP_1620_BY2 = new double [] {
		121, 112, 103, 95, 88, 82, 77, 72, 68, 63,
		60, 56, 53, 51, 48, 46, 44, 42, 40, 38,
		35, 33, 31, 29, 26, 24, 22, 20, 18, 16,
		14, 12, 11, 10, 9, 8, 7, 7, 7, 7,
		7, 7, 8, 8, 9, 9, 9, 9, 9, 10,
		10, 10, 10, 10, 10, 10, 10, 11, 11, 11,
		11, 11, 12, 12, 12, 12, 13, 13, 13, 14,
		14, 14, 14, 15, 15, 15, 15, 15, 16, 16,
		16, 16, 16, 16, 16, 16, 15, 15, 14, 13,
		13.1, 12.5, 12.2, 12.0, 12.0, 12.0, 12.0, 12.0, 12.0, 11.9,
		11.6, 11.0, 10.2, 9.2, 8.2, 7.1, 6.2, 5.6, 5.4, 5.3, 
		 5.4,  5.6,  5.9, 6.2, 6.5, 6.8, 7.1, 7.3, 7.5, 7.6,
		 7.7, 7.3, 6.2, 5.3, 2.7, 1.4, -1.2, -2.8, -3.8, -4.8,
		 -5.5, -5.3, -5.6, -5.7, -5.9, -6.0, -6.3, -6.5, -6.2, -4.7,
		 -2.8, -0.1, 2.6, 5.3, 7.7, 10.4, 13.3, 16.0, 18.2, 20.2,
		 21.1, 22.4, 23.5, 23.8, 24.3, 24.0, 23.9, 23.9, 23.7, 24.0,
		 24.3, 25.3, 26.2, 27.3, 28.2, 29.1, 30.0, 30.7, 31.4, 32.2,
		 33.1, 34.0, 35.0, 36.5, 38.3, 40.2, 42.2, 44.5, 46.5, 48.5,
		 50.5, 52.2, 53.8, 54.9, 55.8, 56.9, 58.3, 60.0, 61.6, 63.0,
		 65.0
	};
	
	/**
	 * Estimates the difference between Dynamic Time (TD) and Universal
	 * Time (UT) based on the year.  The result is in seconds.
	 * 
	 * This uses the simple formulas (10.1 and 10.2) for years
	 * less than 1600 or greater than 2000.  It uses a lookup
	 * table for values between 1600 and 2000.
	 */
	public static double estimateDynamicTimeOffsetForYear(int year) {
		
		//standard formulas for periods outside lookup table
		if (year < 948) {
			double t = (year - 2000) / 100.0;
			return 2177 + 497*t + 44.1*t*t;			//10.1
		}
		if (year <= 1600 || year >= 2000) {
			double t = (year - 2000) / 100.0;
			double dt = 102 + 102 * t + 25.3*t*t;	//10.2
			if (year >= 2000 & year <= 2100)
				dt += 0.37 * (year - 2100);
			return dt;
		}
		
		//interpolate between 98.8 (1600) and 121 (1620)
		if (year < 1620)
			return 98.8 + (121 - 98.8) * (year - 1600) / 20.0;
		
		//for 1620-1998 use lookup table
		if (year < 2000) {
			int index = (year - 1620) / 2;
			if (year % 2 == 0)
				return LOOKUP_1620_BY2[index];
			else
				return (LOOKUP_1620_BY2[index] + LOOKUP_1620_BY2[index+1]) / 2.0;
		}
		
		return 0;	//not stated, but implied near zero or negative from 1600-2000
	}
	
	//NOTE: we don't really need these equations since we use the
	//      lookup tables for these time periods.
	
	/**
	 * Valid from 1800-1997 with maximum error of 2.3 seconds
	 */
	static Utils.PolynomialFunction DELTA_T_BETTER_1800_1997 = new Utils.PolynomialFunction(
			new double [] {-1.02, 
					91.02, 265.90, -839.16, -1545.20,
					3603.62, 4385.98, -6993.23, -6090.04,
					6298.12, 4102.86, -2137.64, -1081.51});
	
	/**
	 * Valid from 1800-1899 with maximum error of 0.9 seconds
	 */
	static Utils.PolynomialFunction DELTA_T_BETTER_1800_1899 = new Utils.PolynomialFunction(
			new double [] {-2.50, 
					    228.95,    5218.61,   56282.84, 324011.78,
					1061660.75, 2087298.89, 2513807.78,
					1818961.41,  727058.63,  123563.95});
	
	/**
	 * Valid from 1900-1997 with maximum error of 0.9 seconds
	 */
	static Utils.PolynomialFunction DELTA_T_BETTER_1900_1997 = new Utils.PolynomialFunction(
			new double [] {-2.44, 
					87.24, 815.2, -3637.80, -18756.33,
					124906.15, -303191.19, 372919.88,
					-232424.66, 58353.42});
	
}
