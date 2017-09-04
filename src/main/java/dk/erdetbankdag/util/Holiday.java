/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.erdetbankdag.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Holiday {
    public static boolean isItHoliday() {
        Calendar cal = GregorianCalendar.getInstance();
        return isItHoliday(cal);
    }
    /**
     * Taget fra http://www.finansraadet.dk/bankkunde/pages/bankhelligdage.aspx
     * Helligdage og banklukkedage 2016
    1. januar 2016 - Nytårsdag
    24. marts 2016 – Skærtorsdag
    25. marts 2016 - Langfredag
    28. marts 2016 - 2. påskedag
    22. april 2016 - Bededag
    5. maj 2016 - Kr. Himmelfartsdag
    6. maj 2016 – Banklukkedag 
    16. maj 2016 - 2. pinsedag
    5. juni 2016 - Grundlovsdag 
    24. december 2016 - Juleaftensdag *
    25. december 2016 - 1. juledag
    26. december 2016 - 2. juledag
    31. december 2016 - Nytårsaftensdag *
     * @author jacobborella
     */
    public static boolean isItHoliday(Calendar cal) {
        if(Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK) ||
           Calendar.SUNDAY   == cal.get(Calendar.DAY_OF_WEEK)) {
            //its weekend
            return true;
        }
        if(Calendar.JANUARY == cal.get(Calendar.MONTH) && 1 == cal.get(Calendar.DAY_OF_MONTH)) {
            //new years day
            return true;
        }
        Date easterSunday = EasterCalculator.calculateEasterSunday(cal.get(Calendar.YEAR));
        Calendar easterCal = GregorianCalendar.getInstance();
        easterCal.setTime(easterSunday);
        easterCal.add(Calendar.DAY_OF_MONTH, -3);
        if(sameDate(easterCal, cal)) return true;//skærtorsdag
        easterCal.add(Calendar.DAY_OF_MONTH, 1);
        if(sameDate(easterCal, cal)) return true;//langfredag
        easterCal.add(Calendar.DAY_OF_MONTH, 3);
        if(sameDate(easterCal, cal)) return true;//2. påskedag
        
        easterCal.add(Calendar.DAY_OF_MONTH, 25);
        if(sameDate(easterCal, cal)) return true;//store bededag
        
        easterCal.add(Calendar.DAY_OF_MONTH, 13);
        if(sameDate(easterCal, cal)) return true;//kristi himmelfart

        easterCal.add(Calendar.DAY_OF_MONTH, 1);
        if(sameDate(easterCal, cal)) return true;//klemmedagen
        
        easterCal.setTime(easterSunday);
        easterCal.add(Calendar.WEEK_OF_MONTH, 7);
        easterCal.add(Calendar.DAY_OF_MONTH, 1);
        if(sameDate(easterCal, cal)) return true;//2. pinsedag
        
        if(Calendar.JUNE == cal.get(Calendar.MONTH) && 5 == cal.get(Calendar.DAY_OF_MONTH)) {
            //grundlovsdag
            return true;
        }
        
        if(Calendar.DECEMBER == cal.get(Calendar.MONTH)) {
            //jul
            return 24 == cal.get(Calendar.DAY_OF_MONTH) ||
                   25 == cal.get(Calendar.DAY_OF_MONTH) ||
                   26 == cal.get(Calendar.DAY_OF_MONTH);
        }

        if(Calendar.DECEMBER == cal.get(Calendar.MONTH) && 31 == cal.get(Calendar.DAY_OF_MONTH)) {
            //nytårsaftensdag
            return true;
        }

        return false;
    }
    private static boolean sameDate(Calendar date0, Calendar date1) {
        return date0.get(Calendar.YEAR) == date1.get(Calendar.YEAR) &&
               date0.get(Calendar.MONTH) == date1.get(Calendar.MONTH) &&
               date0.get(Calendar.DAY_OF_MONTH) == date1.get(Calendar.DAY_OF_MONTH);
    }
}
