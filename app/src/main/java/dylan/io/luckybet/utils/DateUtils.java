package dylan.io.luckybet.utils;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    @NonNull
    public static Date getDaysBefore(@NonNull Date date, int days) {
        return new Date(date.getTime() - days * 24 * 60 * 60 * 1000);
    }

    @NonNull
    public static Date getDaysAfter(@NonNull Date date, int days) {
        return new Date(date.getTime() + days * 24 * 60 * 60 * 1000);
    }

    public static Date getMatchStartTime(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public static Date getMatchEndTime(@NonNull Date date) {
        return getDaysAfter(getMatchStartTime(date), 1);
    }
}
