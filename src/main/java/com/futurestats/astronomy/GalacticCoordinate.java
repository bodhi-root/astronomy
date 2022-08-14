package com.futurestats.astronomy;

import com.futurestats.astronomy.angles.Angle;

/**
 * 
 */
public class GalacticCoordinate {

	Angle latitude;
	Angle longitude;
	
	public GalacticCoordinate(Angle latitude, Angle longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Angle getLatitude() {
		return latitude;
	}
	public Angle getLongitude() {
		return longitude;
	}
	
	public String toString() {
		return "(lat, lon) = (" + latitude.toDmsString() + ", " + longitude.toDmsString() + ")";
	}
	
}
