package com.yucheng.estm.utils;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapHelper {
    private static Logger log = Logger.getLogger(MapHelper.class);

    public static Map<String,String> conver2Map(Object obj) {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                if(value != null){
                    map.put(fieldName, value.toString());
                }else{
                    map.put(fieldName, "");
                }
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
            }
        }

        return map;
    }
}