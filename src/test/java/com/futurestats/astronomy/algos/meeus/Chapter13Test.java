package com.futurestats.astronomy.algos.meeus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.futurestats.astronomy.algos.meeus.Structs.EclipticalCoordinate;
import com.futurestats.astronomy.algos.meeus.Structs.EquatorialCoordinate;
import com.futurestats.astronomy.algos.meeus.Structs.HorizontalCoordinate;

/**
 * NOTE: The calculator below can be useful for double-checking these 
 * conversions:
 * https://ned.ipac.caltech.edu/coordinate_calculator
 */
public class Chapter13Test {

	@Test
	public void test() {
		example13a();
		example13b();
	}
	
	public void example13a() {
		double alpha = 116.328942;
		double delta = 28.026183;
		double epsilon = 23.4392911;
		
		EquatorialCoordinate x = new EquatorialCoordinate(alpha, delta);
		
		EclipticalCoordinate x2 = Chapter13.equatorialToEcliptic(x, epsilon);
		assertEquals(6.684169796279848, x2.latitude, 1e-6);
		assertEquals(113.21562957874626, x2.longitude, 1e-6);
		
		EquatorialCoordinate x3 = Chapter13.eclipticToEquatorial(x2, epsilon);
		assertEquals(alpha, x3.rightAscension, 1e-6);
		assertEquals(delta, x3.declination, 1e-6);
	}
	
	public void example13b() {
		
		//given:
		double alpha = Utils.hmsToDegrees(23, 9, 16.641);
		double delta = Utils.dmsToDegrees(-6, 43, 11.61);
		
		int year = 1987;
		int month = 4;
		int day = 10;
		double dayFrac = Utils.hmsToDays(19, 21, 0);
		
		double longitude = Utils.dmsToDegrees(77, 3, 56);
		double latitude = Utils.dmsToDegrees(38, 55, 17);
		
		double nutation = Utils.dmsToDegrees(0, 0, -3.868);
		double epsilon = Utils.dmsToDegrees(23, 26, 36.87);
		
		//solve:
		EquatorialCoordinate x = new EquatorialCoordinate(alpha, delta);
		
		double jd = Chapter7.julianDateFor(year, month, day + dayFrac);
		double gmst = Chapter12.gmst(jd);
		//System.out.println(Utils.degreesToHmsString(gmst));
		
		double gmstApparent = gmst + nutation * TrigDegrees.cos(epsilon);
		assertEquals(128.73688749278548, gmstApparent, 1e-6);
		//System.out.println(Utils.degreesToHmsString(gmstApparent));
		
		double localSiderealTime = gmstApparent - longitude;
		//double localSiderealTime = -295.647867 + x.rightAscension;
		//System.out.println(gmstApparent - longitude - x.rightAscension);
		//System.out.println(localSiderealTime - x.rightAscension);
		
		HorizontalCoordinate x2 = Chapter13.equatorialToHorizontal(
				x, localSiderealTime, latitude);
		
		System.out.println(x2);
		//not getting the right answer... should be A = 68.0337, h = 15.1249
		
	}
	
}
