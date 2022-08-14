package com.futurestats.astronomy.algos.meeus;

import com.futurestats.astronomy.algos.meeus.Structs.EquatorialCoordinate;
import com.futurestats.astronomy.algos.meeus.Structs.HorizontalCoordinate;
import com.futurestats.astronomy.algos.meeus.Structs.EclipticalCoordinate;

public class Chapter13 {

	// alpha  = right ascension
	// delta  = declination (positive for north, negative for south)
	// lambda = ecliptical longitude
	// beta   = ecliptical latitude
	// l      = galactic longitude
	// b      = galactic latitude
	// h      = altitude (positive above horizon, negative if below)
	// A      = azimuth
	// epsilon = obliquity of the ecliptic
	// psi = observer' latitude (positive for norther hemisphere, negative south)
	// H = local hour angle (measured westwards from the South)
	
	public static final double EPSILON_J2000 = 23.4392911;
	
	/**
	 * Convert equatorial coordinates to ecliptic.  (Formula 13.1 & 13.2)
	 * 
	 * Epsilon is the obliquity of the ecliptic.  For J2000 this is
	 * 23.4392911 degrees.
	 */
	public static EclipticalCoordinate equatorialToEcliptic(
			EquatorialCoordinate coord, double epsilon) {
		
		double sin_alpha = TrigDegrees.sin(coord.rightAscension);
		double cos_alpha = TrigDegrees.cos(coord.rightAscension);
		double sin_delta = TrigDegrees.sin(coord.declination);
		double cos_delta = TrigDegrees.cos(coord.declination);
		double tan_delta = TrigDegrees.tan(coord.declination);
		double sin_eps   = TrigDegrees.sin(epsilon);
		double cos_eps   = TrigDegrees.cos(epsilon);
		
		//double tan_lambda = ((sin_alpha * cos_eps) + (tan_delta * sin_eps)) / cos_alpha;
		//double sin_beta = (sin_delta * cos_eps) - (cos_delta * sin_eps * sin_alpha);
		
		double lambda = TrigDegrees.atan2((sin_alpha * cos_eps) + (tan_delta * sin_eps), cos_alpha);
		double beta = TrigDegrees.asin((sin_delta * cos_eps) - (cos_delta * sin_eps * sin_alpha));
		
		return new EclipticalCoordinate(lambda, beta);
	}
	
	/**
	 * Convert ecliptic coordinates to equatorial.  (Formula 13.3 & 13.4)
	 */
	public static EquatorialCoordinate eclipticToEquatorial(
			EclipticalCoordinate coord, double epsilon) {
		
		double sin_lambda = TrigDegrees.sin(coord.longitude);
		double cos_lambda = TrigDegrees.cos(coord.longitude);
		double sin_beta = TrigDegrees.sin(coord.latitude);
		double cos_beta = TrigDegrees.cos(coord.latitude);
		double tan_beta = TrigDegrees.tan(coord.latitude);
		double sin_eps = TrigDegrees.sin(epsilon);
		double cos_eps = TrigDegrees.cos(epsilon);
		
		//double tan_alpha = ((sin_lambda * cos_eps) - (tan_beta * sin_eps)) / cos_lambda;
		//double sin_delta = (sin_beta * cos_eps) + (cos_beta * sin_eps * sin_lambda);
		
		double alpha = TrigDegrees.atan2((sin_lambda * cos_eps) - (tan_beta * sin_eps), cos_lambda);
		double delta = TrigDegrees.asin((sin_beta * cos_eps) + (cos_beta * sin_eps * sin_lambda));
		
		return new EquatorialCoordinate(alpha, delta);
	}
	
	/**
	 * Converts equatorial coordinates to horizontal.  This requires the local
	 * local sidereal time (theta) and geographical latitude (psi) of the
	 * observer.  (Formula 13.5 & 13.6)
	 */
	public static HorizontalCoordinate equatorialToHorizontal(
			EquatorialCoordinate coord, double localSiderealTime, double latitude) {
		
		double H = localSiderealTime - coord.rightAscension;
		double sin_delta = TrigDegrees.sin(coord.declination);
		double cos_delta = TrigDegrees.cos(coord.declination);
		double tan_delta = TrigDegrees.tan(coord.declination);
		double sin_psi = TrigDegrees.sin(latitude);
		double cos_psi = TrigDegrees.cos(latitude);
		double sin_H = TrigDegrees.sin(H);
		double cos_H = TrigDegrees.cos(H);
		
		//double tan_A = sin_H / ((cos_H * sin_psi) - (tan_delta * cos_psi));
		//double sin_h = (sin_psi * sin_delta) + (cos_psi * cos_delta * cos_H);
		
		double A = TrigDegrees.atan2(sin_H, (cos_H * sin_psi) - (tan_delta * cos_psi));
		double h = TrigDegrees.asin((sin_psi * sin_delta) + (cos_psi * cos_delta * cos_H));
		
		return new HorizontalCoordinate(A, h);
	}
	
	/**
	 * Converts horizontal coordinates to equitorial.  This requires the local
	 * local sidereal time (theta) and geographical latitude (psi) of the
	 * observer.
	 */
	public static EquatorialCoordinate horizontalToEquatorial(
			HorizontalCoordinate coord, double localSiderealTime, double latitude) {
		
		double sin_h = TrigDegrees.sin(coord.altitude);
		double cos_h = TrigDegrees.cos(coord.altitude);
		double tan_h = TrigDegrees.tan(coord.altitude);
		double sin_A = TrigDegrees.sin(coord.azimuth);
		double cos_A = TrigDegrees.cos(coord.azimuth);
		double sin_psi = TrigDegrees.sin(latitude);
		double cos_psi = TrigDegrees.cos(latitude);
		
		double tan_H = sin_A / ((cos_A * sin_psi) + (tan_h * cos_psi));
		double sin_delta = (sin_psi * sin_h) - (cos_psi * cos_h * cos_A);
		
		double H = TrigDegrees.atan(tan_H);
		double delta = TrigDegrees.asin(sin_delta);
		
		double alpha = localSiderealTime - H;
		
		return new EquatorialCoordinate(alpha, delta);
	}
	
}
