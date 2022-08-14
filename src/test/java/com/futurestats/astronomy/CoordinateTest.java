package com.futurestats.astronomy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.futurestats.astronomy.angles.Angle;
import com.futurestats.astronomy.angles.Degrees;


public class CoordinateTest {

	@Test
	public void test() {
		example13a();
	}
	
	/**
	 * Example 13a from Astronomical Algorithms.
	 */
	public void example13a() {
		double alpha = 116.328942;
		double delta = 28.026183;
		Angle epsilon = Degrees.of(23.4392911);
		
		EquatorialCoordinate x = EquatorialCoordinate.fromDegrees(alpha, delta);
		
		EclipticalCoordinate x2 = x.toEclipticalCoordinate(epsilon);
		assertEquals(6.684169796279848, x2.getLatitude().toDegrees().value(), 1e-6);
		assertEquals(113.21562957874626, x2.getLongitude().toDegrees().value(), 1e-6);
		
		EquatorialCoordinate x3 = x2.toEquatorialCoordinate(epsilon);
		assertEquals(alpha, x3.getRightAscension().toDegrees().value(), 1e-6);
		assertEquals(delta, x3.getDeclination().toDegrees().value(), 1e-6);
	}
	
}
