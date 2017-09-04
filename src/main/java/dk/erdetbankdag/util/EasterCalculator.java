package dk.erdetbankdag.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Taget fra http://denstoredanske.dk/Sprog,_religion_og_filosofi/Religion_og_mystik/Folkekirkens_helligdage/påske/påske_(Beregning_af_påskedagens_dato)
 * 
 * Beregning af påskedag
De indviklede tabeller, som ledsagede den gregorianske reform, blev af C.F. Gauss omsat i følgende regel, der gælder for både den julianske og den gregorianske kalender:
Hvis T betegner årstallet, og M og N er to tal, som vil blive forklaret nedenfor, og hvis
a er resten ved divisionen T/19,
b er resten ved divisionen T/4,
c er resten ved divisionen T/7,
d er resten ved divisionen (19a+M)/30,
e er resten ved divisionen (2b+4c+6d+N)/7,
så er påskedag den (22+d+e). marts eller den (d+e-9). april, dog med følgende undtagelser:
1. Hvis d = 29 og e = 6, er påskedag ikke den 26., men den 19. april.
2. Hvis d = 28 og e = 6 og desuden a > 10, er påskedag ikke den 25., men den 18. april.
Tallene M og N er i den julianske kalender konstante, nemlig M = 15 og N = 6. I den gregorianske kalender skifter de ofte med århundredet og beregnes således:
Hvis k er årstallets to første cifre (hundredtallet), og p er kvotienten af divisionen (13+8k)/25 uden hensyn til resten,
q er kvotienten af divisionen k/4 uden hensyn til resten,
så er M resten ved divisionen (15-p+k-q)/30,
og N er resten ved divisionen (4+k-q)/7.
For årene 1900 til 2099 er M = 24, N = 5, og for 1800 til 1899 var M = 23, N = 4.
Påskedag kan tidligst falde 22. marts og senest 25. april.
Eksempel. Find datoen for påskedag i år 2010. Man finder a = 15, b = 2, c = 1, d = 9, e = 4. Da 22+d+e = 35 og d+e-9 = 4, falder påskedag den 4. april.
* 
 * @author jacobborella
 */
public class EasterCalculator {
    public static Date calculateEasterSunday(int year) {
        if(year > 9999) throw new IllegalArgumentException("year " + year  + " not supported.");
        int M = 24;
        int N = 5;
        if(year < 1900 || year > 2099) {
            int k = year / 100;
            int p = (13+8*k)/25;
            int q = k/4;
            M = (15-p+k-q)%30;
            N = (4+k-q)%7;
        }
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (19 * a + M) % 30;
        int e = (2*b+4*c+6*d+N) % 7;
        
        Calendar calendar = GregorianCalendar.getInstance();
        
        int month = 0;
        int day = 0;
        
        if(d == 29 && e == 6) {
            month = 3;
            day = 19;
        } else if(d == 28 && e == 6 && a > 10) {
            month = 3;
            day = 18;
        } else {
            int march = (22+d+e);
            int april = (d+e-9);
            if(march > 0 && march <= 31) {
                month = 2;
                day = march;
            } else {
                month = 3;
                day = april;
            }
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return new Date(calendar.getTimeInMillis());
    }
}
