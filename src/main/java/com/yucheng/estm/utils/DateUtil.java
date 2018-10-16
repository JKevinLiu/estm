package com.yucheng.estm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String formatYYYYMMDDHHmmss(Date date){
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String dateStr = df.format(date);
        return dateStr;
    }
}