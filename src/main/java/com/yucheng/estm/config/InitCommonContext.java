package com.yucheng.estm.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InitCommonContext implements InitializingBean {
    private static Map<String, String> busTypeMap = new ConcurrentHashMap<>();


    public static Map<String, String> getBusTypeMap() {
        return busTypeMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        busTypeMap.put("common", "common");
    }
}