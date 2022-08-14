package com.futurestats.astronomy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.futurestats.astronomy.algos.meeus.Chapter12;
import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;

/**
 * Sources:
 * Good blog entry with sample code:
 *   https://squarewidget.com/astronomical-calculations-sidereal-time/
 * Online calculator: https://www.localsiderealtime.com/
 */
public class SiderealTime {

	//CONSIDER: what would good object-oriented design look like here
	//SiderealTime(Angle time, Angle longitude)?
	// - with longitude = 0 for GMST
	// - there's 2 other variants though, isn't there?
	// - and what's up with ERA?
	
	/**
	 * Greenwich Mean Sidereal Time
	 */
	public static Angle gmst(JulianDate date) {
		double deg = Chapter12.gmst(date.value());
		return Degrees.of(deg);
	}
	
	/**
	 * Local Mean Sidereal Time.  This calculates GMST and then adjusts
	 * it based on the (East) longitude of the local location.
	 */
	public static Angle lmst(JulianDate date, Angle longitude) {
		Angle gmst = gmst(date);
		Angle lmst = gmst.add(longitude);
		return lmst.normalize();
	}
	
	/**
	 * Converts the given LMST to a LocalTime on the given date/longitude.
	 * This uses the calculation given here:
	 * 
	 * https://astronomy.stackexchange.com/questions/29471/how-to-convert-sidereal-time-to-local-time
	 * 
	 * which isn't exact, but it's pretty close (within a few seconds on the
	 * sample I tried).
	 */
	public static LocalTime lmstToLocalTime(Angle lmst, LocalDate date, Angle longitude) {
		ZonedDateTime zdate = ZonedDateTime.of(date, LocalTime.NOON, ZoneId.systemDefault());
		Angle lmst0 = lmst(JulianDate.from(zdate), longitude);
		double hours = lmst.subtract(lmst0).toHours().value() / 1.00273790935;
		double nanos = hours * 3600000000000L;
		return zdate.plusNanos((long)nanos).toLocalTime();
	}
	
}
