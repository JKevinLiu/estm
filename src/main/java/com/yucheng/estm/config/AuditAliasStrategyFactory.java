package com.yucheng.estm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuditAliasStrategyFactory {
    /*private final static Logger logger = LoggerFactory.getLogger(AuditAliasStrategyFactory.class);

    @Autowired
    private final Map<String, AuditAliasStrategy> strategyMap = new ConcurrentHashMap<>();

    public AuditAliasStrategy getAuditAliasStrategy(int busType){
        String strategyType = InitCommonContext.getBusTypeMap().get(busType);
        return this.strategyMap.get(strategyType);
    }*/

}