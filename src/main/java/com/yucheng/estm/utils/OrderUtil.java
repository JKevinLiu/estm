package com.yucheng.estm.utils;

import com.yucheng.estm.entity.OutUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderUtil {
    public static String createOrderId(OutUser outUser){
        String number = outUser.getPhone().substring(6,10);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMMMYYDDHHmmss");
        String time = dateFormat.format(date);

        return number + time;
    }

}