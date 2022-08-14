package astronomy;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.futurestats.astronomy.JulianDate;

public class Test {

	public static void main(String [] args) { 
		System.out.println(ZonedDateTime.now());
		System.out.println(JulianDate.from(2022,8,5.5));
		System.out.println(JulianDate.from(ZonedDateTime.of(LocalDate.of(2022,8,5), LocalTime.NOON, ZoneId.of("GMT"))));
		
		System.out.println(JulianDate.of(0).toDateTime(ZoneId.of("GMT")));
		System.out.println(JulianDate.now());
		
		LocalDate d1 = LocalDate.of(1852, 10, 4);
		LocalDate d2 = LocalDate.of(1852, 10, 15);
		System.out.println(java.time.temporal.ChronoUnit.DAYS.between(d1,  d2));
		
		System.out.println(LocalDate.of(-1, 1,1));
		
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt.toString());
		
		Instant instant = Instant.now();
		System.out.println(instant);
		
		System.out.println(instant.atZone(ZoneOffset.systemDefault()));
		
		
		System.out.println(ZoneOffset.systemDefault());
		
		//System.out.println(dt.toInstant(tz));
		
		System.out.println((int)5.5);
		System.out.println((int)-5.5);
		System.out.println(Math.floor(-5.5));
		
	}
	
}
