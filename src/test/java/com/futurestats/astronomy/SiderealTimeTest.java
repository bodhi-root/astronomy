package com.futurestats.astronomy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;
import com.futurestats.astronomy.angles.Hours;

public class SiderealTimeTest {

	public static void main(String [] args) {
		//JulianDate date = JulianDate.of(2446895.5);
		JulianDate date = JulianDate.now();
		
		Angle gmst = SiderealTime.gmst(date);
		System.out.println("GMST: " + gmst.toDegrees());
		System.out.println("GMST: " + gmst.toHours());
		
		Degrees longitude = Degrees.of(-84.51);
		
		Angle lmst = SiderealTime.lmst(JulianDate.now(), longitude);
		System.out.println("LMST: " + lmst.toDegrees());
		System.out.println("LMST: " + lmst.toHours());
		
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = SiderealTime.lmstToLocalTime(Hours.of(13.5), localDate, longitude);
		System.out.println(localTime);
		ZonedDateTime zd = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault());
		System.out.println(SiderealTime.lmst(JulianDate.from(zd), longitude).toHours());
	}
	
}
