package com.futurestats.astronomy.algos;

/**
 * Calculates sunrise and sunset for different points on earth.
 * 
 * Source:
 * 	Almanac for Computers, 1990
 * 	published by Nautical Almanac Office
 * 	United States Naval Observatory
 * 	Washington, DC 20392
 * 
 * from: http://www.edwilliams.org/sunrise_sunset_algorithm.htm (2022-08-05)
 * verified with calculator: https://gml.noaa.gov/grad/solcalc/
 */
public class SunriseSunset {

	//degrees:
	public static final double OFFICIAL_ZENITH     = 90 + 50.0/60.0;
	public static final double CIVIL_ZENITH        = 96;
	public static final double NAUTICAL_ZENITH     = 102;
	public static final double ASTRONOMICAL_ZENITH = 108;
		
	/**
	 * Calculates sunrise or sunset.  Latitude, longitude, and zenith
	 * should be given in degrees.  Longitude is positive for east.
	 * Result is in hours UT.
	 */
	public static double calculate(int year, int month, int day, 
			double latitude, double longitude, double zenith, 
			boolean sunrise) {

		//1. first calculate the day of the year
		int N1 = (int)Math.floor(275 * month / 9.0);
		int N2 = (int)Math.floor((month + 9) / 12.0);
		int N3 = (1 + (int)Math.floor((year - 4 * Math.floor(year / 4) + 2) / 3));
		int N = N1 - (N2 * N3) + day - 30;
		
		//2. convert the longitude to hour value and calculate an approximate time

		double lngHour = longitude / 15;
		
		double t = sunrise ? 
				N + ((6 - lngHour) / 24) : 
		        N + ((18 - lngHour) / 24);

		//3. calculate the Sun's mean anomaly
		
		double M = (0.9856 * t) - 3.289;

	    //4. calculate the Sun's true longitude
		
		double L = M + (1.916 * sin(M)) + 
				(0.020 * sin(2*M)) 
				+ 282.634;
	
		L = normalize(L);

		//5a. calculate the Sun's right ascension
		
		double RA = atan(0.91764 * tan(L));
		RA = normalize(RA);

		//5b. right ascension value needs to be in the same quadrant as L

		int Lquadrant  = ((int)Math.floor( L/90)) * 90;
		int RAquadrant = ((int)Math.floor(RA/90)) * 90;
		RA = RA + (Lquadrant - RAquadrant);

		//5c. right ascension value needs to be converted into hours

		RA = RA / 15;

		//6. calculate the Sun's declination

		double sinDec = 0.39782 * sin(L);
		double cosDec = cos(asin(sinDec));

		//7a. calculate the Sun's local hour angle
		
		double cosH = (cos(zenith) - (sinDec * sin(latitude))) / 
				(cosDec * cos(latitude));
		
		
		//the sun never rises on this location (on the specified date)
		if (cosH >  1)
			return Double.NaN;
		  
		//the sun never sets on this location (on the specified date)
		if (cosH < -1)
			return Double.NaN;
        
		//7b. finish calculating H and convert into hours
		
		double H = sunrise ? 360 - acos(cosH) : acos(cosH);
		
		H = H / 15;

		//8. calculate local mean time of rising/setting
		
		double T = H + RA - (0.06571 * t) - 6.622;
		
		//9. adjust back to UTC  (UT is in hours)
		
		double UT = T - lngHour;
		while (UT < 0)
			UT += 24;
		while (UT > 24)
			UT -= 24;
		
		return UT;
	}
	
	public static double normalize(double deg) {
		if (deg < 0)
			return deg % 360 + 360;
		else if (deg > 360)
			return deg % 360;
		else
			return deg;
	}
	
	public static double sin(double deg) {
		return Math.sin(deg*Math.PI/180);
	}
	public static double cos(double deg) {
		return Math.cos(deg*Math.PI/180);
	}
	public static double tan(double deg) {
		return Math.tan(deg*Math.PI/180);
	}
	
	public static double asin(double x) {
		return Math.asin(x)*180/Math.PI;
	}
	public static double acos(double x) {
		return Math.acos(x)*180/Math.PI;
	}
	public static double atan(double x) {
		return Math.atan(x)*180/Math.PI;
	}
	
}
