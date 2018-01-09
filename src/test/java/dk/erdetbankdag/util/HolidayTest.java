package dk.erdetbankdag.util;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
public class HolidayTest {
	@Test
	public void testEaster2018() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.YEAR, 2018);
		assertTrue(Holiday.isItHoliday(cal), "April 1st is a Easter day");
	}
	@Test
	public void testIsBankday() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		cal.set(Calendar.YEAR, 2018);
		assertFalse(Holiday.isItHoliday(cal), "Monday jan 8th is a bankday");
		cal.set(Calendar.DAY_OF_MONTH, 9);
		assertFalse(Holiday.isItHoliday(cal), "Tuesday jan 9th is a bankday");
		cal.set(Calendar.DAY_OF_MONTH, 10);
		assertFalse(Holiday.isItHoliday(cal), "Wednesday jan 10th is a bankday");
		cal.set(Calendar.DAY_OF_MONTH, 11);
		assertFalse(Holiday.isItHoliday(cal), "Thursday jan 11th is a bankday");
		cal.set(Calendar.DAY_OF_MONTH, 12);
		assertFalse(Holiday.isItHoliday(cal), "Friday jan 12th is a bankday");
	}
	@Test
	public void testSunday() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.DAY_OF_MONTH, 13);
		assertTrue(Holiday.isItHoliday(cal), "Saturday jan 13th is a bankday");
		cal.set(Calendar.DAY_OF_MONTH, 14);
		assertTrue(Holiday.isItHoliday(cal), "Sunday jan 14th isn't a bankday");
	}
}
