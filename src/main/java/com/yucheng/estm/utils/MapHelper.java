package com.yucheng.estm.utils;

import com.yucheng.estm.constants.CommonContant;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
                    String s = value.toString();
                    s = doConver(fieldName, s);
                    map.put(fieldName, s);
                }else{
                    map.put(fieldName, "");
                }
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
            }
        }

        return map;
    }

    private static String doConver(String fieldName, String s) {

        StringBuilder retStr = new StringBuilder();

        if(fieldName.equals("appType1")){
            String[] indexs = s.split(",");
            List<String> list = Arrays.asList(indexs);

            for(int i = 0; i < CommonContant.unCheckAppType.length; i++){
                if(list.contains(i + "")){
                    retStr.append(CommonContant.checkedAppType[i]);
                }else{
                    retStr.append(CommonContant.unCheckAppType[i]);
                }
                retStr.append(" ");
            }
        }else if(fieldName.equals("appType2")){
            String[] indexs = s.split(",");
            List<String> list = Arrays.asList(indexs);

            for(int i = 0; i < CommonContant.unCheckResType.length; i++){
                if(list.contains(i  + "")){
                    retStr.append(CommonContant.checkedResType[i]);
                }else{
                    retStr.append(CommonContant.unCheckResType[i]);
                }
                retStr.append(" ");
            }
        }else if(fieldName.equals("appForm")){
            String[] indexs = s.split(",");
            List<String> list = Arrays.asList(indexs);

            for(int i = 0; i < CommonContant.unCheckFormType.length; i++){
                if(list.contains(i  + "")){
                    retStr.append(CommonContant.checkedFormType[i]);
                }else{
                    retStr.append(CommonContant.unCheckFormType[i]);
                }
                retStr.append("  ");
            }
        }else if(fieldName.equals("appCert")){
            String[] indexs = s.split(",");
            List<String> list = Arrays.asList(indexs);

            for(int i = 0; i < CommonContant.unCheckCertType.length; i++){
                if(list.contains(i  + "")){
                    retStr.append(CommonContant.checkedCertType[i]);
                }else{
                    retStr.append(CommonContant.unCheckCertType[i]);
                }
                retStr.append("  ");
            }
        }else {
            retStr.append(s);
        }

        return retStr.toString();
    }


}