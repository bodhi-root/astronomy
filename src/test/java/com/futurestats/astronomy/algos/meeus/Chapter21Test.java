package com.futurestats.astronomy.algos.meeus;

import com.futurestats.astronomy.algos.meeus.Structs.EquatorialCoordinate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Chapter21Test {

	@Test
	public void test() {
		EquatorialCoordinate coord = new EquatorialCoordinate(
				Utils.hmsToDegrees( 2, 44, 11.986) + Utils.hmsToDegrees(0, 0, 0.989),
				Utils.dmsToDegrees(49, 13, 42.48 ) + Utils.dmsToDegrees(0, 0, -2.58));
		
		//System.out.println(coord);
		
		double jd0 = Utils.JD_J2000;
		double jd = Chapter7.julianDateFor(2028, 11, 13.19);
		//System.out.println(jd);
		
		Structs.EquatorialCoordinate coord2 = Chapter21.adjust(coord, jd0, jd);
		//System.out.println(coord2);
		
		///RA = +2h 46m 11.331s, DEC = +49d 20' 54.54"
		//System.out.println(Utils.degreesToHmsString(coord2.rightAscension));	
		//System.out.println(Utils.degreesToDmsString(coord2.declination));
		
		assertEquals(41.547213869711086, coord2.rightAscension, 1e-6);
		assertEquals(49.348483110787560, coord2.declination, 1e-6);
		
		jd0 = Chapter7.julianDateFor(-130, 1, 1);
		jd = Chapter7.julianDateFor(2022, 1, 1);
		EquatorialCoordinate zero = new EquatorialCoordinate(0, 0);
		EquatorialCoordinate zero_now = Chapter21.adjust(zero, jd0, jd);
		System.out.println(zero_now);
	}
	
}
