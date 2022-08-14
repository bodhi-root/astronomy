package com.futurestats.astronomy.apps;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.futurestats.astronomy.GeoContext;
import com.futurestats.astronomy.SiderealTime;
import com.futurestats.astronomy.SunriseSunsetCalculator;
import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Hours;

public class DailyChartMaker {

	GeoContext context;
	
	public DailyChartMaker(GeoContext context) {
		this.context = context;
	}
	public DailyChartMaker(Angle latitude, Angle longitude, ZoneId zoneId) {
		this(new GeoContext(latitude, longitude, zoneId));
	}
	
	public static void main(String [] args) {
		//TODO: accept command line parameters
		DailyChartMaker maker = new DailyChartMaker(GeoContext.CINCINNATI);
		maker.printChartForYear(2022, System.out);
	}
	
	public void printChartForYear(int year, PrintStream out) {
		
		SunriseSunsetCalculator ssCalc = new SunriseSunsetCalculator(context);
		
		LocalDate date = LocalDate.of(year, 1, 1);
		do {
			ZonedDateTime sunrise = ssCalc.calculateSunrise(date);
			ZonedDateTime sunset  = ssCalc.calculateSunset(date);
			
			Hours maxEspLmst = Hours.of(13.5);
			LocalTime maxEspLocal = SiderealTime.lmstToLocalTime(
					maxEspLmst, date, context.getLongitude());
			
			Hours lmstOffset = maxEspLmst.subtract(Hours.from(maxEspLocal));
			double offsetValue = lmstOffset.normalize().value();
			if (offsetValue > 12)
				offsetValue -= 24;
			
			out.println(date + ", " + toHours(sunrise.toLocalTime()) 
			                 + ", " + toHours(sunset.toLocalTime())
			                 + ", " + offsetValue
			                 + ", " + toHours(maxEspLocal));
			
			date = date.plusDays(1);
			
		} while(date.getYear() == year);
	}
	
	public static double toHours(LocalTime time) {
		return time.getHour() + 
			   (time.getMinute() / 60.0) +
               (time.getSecond() / 3600.0) + 
               (time.getNano() / 3600000000000L);
	}
	
}
