package com.futurestats.astronomy;

import com.futurestats.astronomy.algos.meeus.Chapter22;
import com.futurestats.astronomy.algos.meeus.Structs;
import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;

public class Ecliptic {

	public static final Angle EPSILON_J2000 = Degrees.of(23.4392911);
	
	public static class Nutation {
		
		Angle longitude;
		Angle obliquity;
		
		public Nutation(Angle longitude, Angle obliquity) {
			this.longitude = longitude;
			this.obliquity = obliquity;
		}
		
		public Angle getLongitude() {
			return longitude;
		}
		public Angle getObliquity() {
			return obliquity;
		}
		
	}
	
	/**
	 * Returns the nutational values for the given date (JDE).
	 */
	public static Nutation getNutationFor(JulianDate jde) {
		Structs.Nutation nutation = Chapter22.nutation(jde.value());
		return new Nutation(
				Degrees.of(nutation.longitude / 3600), 
				Degrees.of(nutation.obliquity / 3600));
	}
	
	/**
	 * Returns the mean obliquity of the ecliptic for the given date (JDE).
	 */
	public static Angle getMeanObliquity(JulianDate jde) {
		return Degrees.of(Chapter22.meanObliquityOfEcliptic(jde.value()));
	}
	
	/**
	 * Returns the true obliquity of the ecliptic for the given date (JDE).
	 * This is the mean obliquity plus the nutational adjustment.
	 */
	public static Angle getObliquity(JulianDate jde) {
		double meanObliquity = Chapter22.meanObliquityOfEcliptic(jde.value());
		Structs.Nutation nutation = Chapter22.nutation(jde.value());
		return Degrees.of(meanObliquity + (nutation.obliquity / 3600));
	}
	
	
}
