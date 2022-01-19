package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static Date parseDate(String dateString, String dateFormat) throws ParseException {
        if("NULL".equals(dateString)) {
            return new Date();
        }

        return new SimpleDateFormat(dateFormat).parse(dateString);
    }

    public static long getDaysOverlap(Date startA, Date endA, Date startB, Date endB) {
        long overlapInMillis = Math.max(
                0,
                Math.min(endA.getTime(), endB.getTime() - Math.max(startA.getTime(), startB.getTime())));

        return TimeUnit.DAYS.convert(overlapInMillis, TimeUnit.MILLISECONDS);
    }
}
