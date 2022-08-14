package com.futurestats.astronomy.algos.meeus;

/**
 * Data structures used by Astronomical Algorithms.  These are written to look
 * more like C-style structs.  All members of a struct are public with no
 * setters, getters, or other methods allowed (except maybe toString()).
 * The intention is to keep the actual algorithms and logic in their
 * respective chapters separate from the data structures but to let
 * the data structures be re-usable without having to remember what
 * chapter they were in.  
 * 
 * NOTE: Putting data structures inside a separate class is non-conventional.  
 * It would be more "Java-like" to define them as separate classes is in
 * their own package. We have put them all in this "Structs" object to 
 * emphasize that these are not intended to be normal Java classes.  Users
 * of these algorithms will also likely define their own classes for these
 * concepts that may use identical names.  Encapsulating the data structures
 * inside the Structs class makes it easier to import and use these data
 * structures in your own code even when you have your own classes with 
 * identical names.
 */
public class Structs {

	public static class AstronomicalDate {
		
		public int year;
		public int month;
		public double day;
		
		public AstronomicalDate(int year, int month, double day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}
		
		public String toString() {
			return String.format("%04d-%02d-%02f", year, month, day);
		}
		
	}
	
	public static class EquatorialCoordinate {
		
		public double rightAscension;		//alpha
		public double declination;			//delta
		
		public EquatorialCoordinate(double ra, double dec) {
			this.rightAscension = ra;
			this.declination = dec;
		}
		
		public String toString() {
			return "(ra, dec) = (" + rightAscension + ", " + declination + ")";
		}
		
	}
	
	public static class EclipticalCoordinate {
		
		public double longitude;			//lambda
		public double latitude;				//beta
		
		public EclipticalCoordinate(double longitude, double latitude) {
			this.longitude = longitude;
			this.latitude = latitude;
		}
		
		public String toString() {
			return "(lon, lat) = (" + longitude + ", " + latitude + ")";
		}
		
	}
	
	public static class HorizontalCoordinate {
		
		public double azimuth;		// A
		public double altitude;		// h
		
		public HorizontalCoordinate(double azimuth, double altitude) {
			this.azimuth = azimuth;
			this.altitude = altitude;
		}
		
		public String toString() {
			return ("(azimuth, altitude) = (" + azimuth + ", " + altitude + ")");
		}
		
	}
	
	/**
	 * Represents nutation of the Earth's rotational axis.  Values are in
	 * seconds.
	 */
	public static class Nutation {
		
		public double longitude;	//delta psi
		public double obliquity;	//delta epsilon
		
		public Nutation(double longitude, double obliquity) {
			this.longitude = longitude;
			this.obliquity = obliquity;
		}
		
		public String toString() {
			return ("Nutation(long=" + longitude + ", obliquity=" + obliquity + ")");
		}
		
	}
	
}
