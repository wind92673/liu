package edu.nuc.light_system.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    private static final Object lockObj =new Object();
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> t1 = sdfMap.get(pattern);
        if (t1 ==null) {
            synchronized (lockObj) {
                t1 = sdfMap.get(pattern);
                if (t1 == null) {
                    t1= new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected SimpleDateFormat initialValue(){
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern,t1);
                }
            }
        }

        return t1.get();
    }
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }
    public static Date parse(String dateStr, String pattern) {
        try{
            return getSdf(pattern).parse(dateStr);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseTimestamp(String timestamp) {
        if (timestamp == null || timestamp.isEmpty()) {
            return new Date();
        }
        return parse(timestamp, "yyyyMMddHHmmss");
    }

    public static Integer extractHourFromTimestamp(String timestamp) {
        if (timestamp == null || timestamp.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            return calendar.get(Calendar.HOUR_OF_DAY);
        }
        Date date = parseTimestamp(timestamp);
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            return calendar.get(Calendar.HOUR_OF_DAY);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
