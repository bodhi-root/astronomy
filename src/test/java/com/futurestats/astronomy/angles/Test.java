package com.futurestats.astronomy.angles;

public class Test {

	public static void main(String [] args) {
		//Angle angle = Hours.of(13.5);
		Angle angle = Angle.fromDms(120, 15, 12.5);
		System.out.println(angle.toDegrees().value());
		System.out.println(angle.toDmsString());
		System.out.println(angle.toHmsString());
		System.out.println(angle.toHours().value());
	}
	
}
