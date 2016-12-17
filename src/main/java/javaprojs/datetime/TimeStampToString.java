package javaprojs.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ram Velury on 12/14/16.
 */
public class TimeStampToString
{
    static long timestamp = 1473998984554L;
    static final String timeFormat = "YYYY-MM-DD hh:MM:ss";

    public static void main(String[] args)
    {
        System.out.println(timeStampFormat(timestamp));
    }

    public static String timeStampFormat(long timestamp) {
        // test for uninitialized time
        if (timestamp == -1L) {
            return "??";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        return sdf.format(new Date(timestamp));
    }
}
