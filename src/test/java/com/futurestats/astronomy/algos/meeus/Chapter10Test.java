package com.futurestats.astronomy.algos.meeus;

import com.futurestats.astronomy.algos.meeus.Chapter10;

public class Chapter10Test {

	public static void main(String [] args) {
		
		int [] years = new int [] {1600, 2000, 2005, 2015};
		for (int i=0; i<years.length; i++)
			System.out.println(Chapter10.estimateDynamicTimeOffsetForYear(years[i]));
		
		for (int year=1620; year<=1998; year++) {
			System.out.println(year + ": " + Chapter10.estimateDynamicTimeOffsetForYear(year));
		}
	}
	
}
