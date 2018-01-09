package dk.erdetbankdag.util;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class HolidayTest {
	@Test
	public void testEaster2018() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.YEAR, 2018);
		assertTrue("April 1st is a Easter day", Holiday.isItHoliday(cal));
	}
	@Test
	public void testIsBankday() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		cal.set(Calendar.YEAR, 2018);
		assertFalse("Monday jan 8th is a bankday", Holiday.isItHoliday(cal));
		cal.set(Calendar.DAY_OF_MONTH, 9);
		assertFalse("Tuesday jan 9th is a bankday", Holiday.isItHoliday(cal));
		cal.set(Calendar.DAY_OF_MONTH, 10);
		assertFalse("Wednesday jan 10th is a bankday", Holiday.isItHoliday(cal));
		cal.set(Calendar.DAY_OF_MONTH, 11);
		assertFalse("Thursday jan 11th is a bankday", Holiday.isItHoliday(cal));
		cal.set(Calendar.DAY_OF_MONTH, 12);
		assertFalse("Friday jan 12th is a bankday", Holiday.isItHoliday(cal));
	}
	@Test
	public void testSunday() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.DAY_OF_MONTH, 13);
		assertTrue("Saturday jan 13th is a bankday", Holiday.isItHoliday(cal));
		cal.set(Calendar.DAY_OF_MONTH, 14);
		assertTrue("Sunday jan 14th isn't a bankday", Holiday.isItHoliday(cal));
	}
}
