package com.yucheng.estm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String formatYYYYMMDDHHmmss(Date date){
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static Date getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date YYYYMMddmmss2Date(String sendDateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return df.parse(sendDateStr);
    }
}