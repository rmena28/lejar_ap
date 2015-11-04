package menasoft.lejarapp.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rmena on 9/13/2015.
 */
public class DateUtil {
    public static Date getFirstDayOfTheWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dayOfTheWeek = c.get(Calendar.DAY_OF_WEEK);
        int p = 2 - dayOfTheWeek;
        if (p > 0) {
            c.add(Calendar.DAY_OF_YEAR, -6);
        } else {
            c.add(Calendar.DAY_OF_YEAR, p);
        }
        return c.getTime();
    }

    public static Date getLastDayOfTheWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dayOfTheWeek = c.get(Calendar.DAY_OF_WEEK);
        int p = 8 - dayOfTheWeek;
        if (p < 7) {
            c.add(Calendar.DAY_OF_YEAR, p);
        }
        return c.getTime();
    }

    public static String getFormattedDate(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public static Date addDay(Date d, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_YEAR, amount);
        return c.getTime();
    }

    public static Date addHours(Date d, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.HOUR_OF_DAY, amount);
        return c.getTime();
    }

    public static Date parse(String string) {
        Date date = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            date = sf.parse(string);
        } catch (Exception ex) {
            Log.e("DateUtil", ex.getMessage(), ex);
        }
        return date;
    }


    public static String parseString(String string) {
        Date date = parse(string);
        if(date!=null) {
            return getFormattedDate(date);
        }
        return "";
    }





}
