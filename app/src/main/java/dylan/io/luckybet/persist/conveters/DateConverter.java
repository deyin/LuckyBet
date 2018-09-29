package dylan.io.luckybet.persist.conveters;

import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private final static SimpleDateFormat FULL_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat MEDIUM_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat SHORT_FORMATTER = new SimpleDateFormat("HH:mm:ss");

    @TypeConverter
    public static Date dateFromYmdHmsString(@NonNull String value) {
        try {
            return FULL_FORMATTER.parse(value);
        } catch (ParseException e) {
            Log.e(DateConverter.class.getName(), "fromYmdHms: ", e);
        }
        return new Date(); // avoid NullPointerException
    }


    @TypeConverter
    public static String dateToYmdHmsString(@NonNull Date date) {
        return FULL_FORMATTER.format(date);
    }

    public static Date dateFromYmdString(@NonNull String value) {
        try {
            return MEDIUM_FORMATTER.parse(value);
        } catch (ParseException e) {
            Log.e(DateConverter.class.getName(), "fromYmdHms: ", e);
        }
        return new Date(); // avoid NullPointerException
    }

    public static String dateToYmdString(@NonNull Date date) {
        return MEDIUM_FORMATTER.format(date);
    }


}