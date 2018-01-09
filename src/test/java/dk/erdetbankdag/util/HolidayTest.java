package dk.erdetbankdag.util;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class HolidayTest {
	@Test
	public void testEaster2018() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		assertTrue(Holiday.isItHoliday(cal), "April 1st is a Easter day");
		
	}
}
