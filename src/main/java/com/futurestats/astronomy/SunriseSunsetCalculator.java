package com.futurestats.astronomy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.futurestats.astronomy.algos.SunriseSunset;
import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;

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
public class SunriseSunsetCalculator {
	
	static ZoneId UTC = ZoneId.of("UTC");
	
	public static final Angle OFFICIAL_ZENITH     = Degrees.of(90 + 50.0/60.0);
	public static final Angle CIVIL_ZENITH        = Degrees.of(96);
	public static final Angle NAUTICAL_ZENITH     = Degrees.of(102);
	public static final Angle ASTRONOMICAL_ZENITH = Degrees.of(108);
	
	GeoContext context;
	Angle zenith;
	
	/**
	 * Creates a calculator for the given position on earth.
	 */
	public SunriseSunsetCalculator(GeoContext context, Angle zenith) {
		this.context = context;
		this.zenith = zenith;
	}
	public SunriseSunsetCalculator(GeoContext context) {
		this(context, OFFICIAL_ZENITH);
	}
	
	public void setGeoContext(GeoContext context) {
		this.context = context;
	}
	public void setZenith(Angle angle) {
		this.zenith = angle;
	}
	
	public ZonedDateTime calculate(LocalDate date, boolean sunrise) {

		Angle latitude = context.getLatitude();
		Angle longitude = context.getLongitude();
		
		double UT = SunriseSunset.calculate(
				date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
				latitude.toDegrees().value(), longitude.toDegrees().value(),
				zenith.toDegrees().value(), sunrise);
		
		
		//convert to ZonedDateTime
		
		long nanos = (long)(UT * 3600000000000L);
		
		LocalTime time = LocalTime.ofNanoOfDay(nanos);
		ZonedDateTime result = ZonedDateTime.of(date, time, UTC)
				.withZoneSameInstant(context.getZoneId());
		
		//sometimes this steps LocalDate back a day, even though LocalTime
		//is correct. force it back to original date.
		if (!result.toLocalDate().equals(date))
			result = ZonedDateTime.of(date, result.toLocalTime(), context.getZoneId());
		
		return result;
	}
	
	public ZonedDateTime calculateSunrise(LocalDate date) {
		return calculate(date, true);
	}
	public ZonedDateTime calculateSunset(LocalDate date) {
		return calculate(date, false);
	}
	
	public ZonedDateTime calculate(int year, int month, int day, boolean sunrise) {
		return calculate(LocalDate.of(year, month, day), sunrise);
	}
	
	public ZonedDateTime calculateSunrise(int year, int month, int day) {
		return calculate(year, month, day, true);
	}
	public ZonedDateTime calculateSunset(int year, int month, int day) {
		return calculate(year, month, day, false);
	}
	
}
