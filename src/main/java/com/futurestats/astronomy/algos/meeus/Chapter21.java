package com.futurestats.astronomy.algos.meeus;

import com.futurestats.astronomy.algos.meeus.Structs.EquatorialCoordinate;

public class Chapter21 {

	/**
	 * Convert the EquatorialCoordinates from their locations on the Julian
	 * Date given by jd0 to the target date jd.
	 */
	public static EquatorialCoordinate adjust(
			EquatorialCoordinate coord, double jd0, double jd) {
		
		double T = (jd0 - 2451545.0) / 36525;
		double t = (jd - jd0) / 36525;
		
		double T2 = T * T;
		double t2 = t * t;
		double t3 = t2 * t;
		
		double x = 2306.2181 + 1.39656 * T - 0.000139 * T2;
		double y = 2004.3109 - 0.85330 * T - 0.000217 * T2;
		
		double zeta  = x*t + (0.30188 - 0.000344*T)*t2 + 0.017998*t3;
		double z     = x*t + (1.09468 + 0.000066*T)*t2 + 0.018203*t3;
		double theta = y*t - (0.42665 + 0.000217*T)*t2 - 0.041833*t3;
		
		//convert arcseconds to degrees:
		zeta /= 3600;
		z /= 3600;
		theta /= 3600;
		
		double alpha_plus_zeta = coord.rightAscension + zeta;
		
		double sin_theta = TrigDegrees.sin(theta);
		double cos_theta = TrigDegrees.cos(theta);
		double sin_delta = TrigDegrees.sin(coord.declination);
		double cos_delta = TrigDegrees.cos(coord.declination);
		double sin_alphazeta = TrigDegrees.sin(alpha_plus_zeta);
		double cos_alphazeta = TrigDegrees.cos(alpha_plus_zeta);
		
		double A = cos_delta * sin_alphazeta;
		double B = (cos_theta * cos_delta * cos_alphazeta) - (sin_theta * sin_delta);
		double C = (sin_theta * cos_delta * cos_alphazeta) + (cos_theta * sin_delta);
		
		return new EquatorialCoordinate(
				TrigDegrees.atan2(A, B) + z,	//right ascension
				TrigDegrees.asin(C)				//declination
				);
	}
	
}
