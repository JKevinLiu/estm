package com.yucheng.estm.config;

import com.yucheng.estm.service.AuditAliasStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuditAliasStrategyFactory {
    private final static Logger logger = LoggerFactory.getLogger(AuditAliasStrategyFactory.class);

    @Autowired
    private final Map<String, AuditAliasStrategy> strategyMap = new ConcurrentHashMap<>();

    public AuditAliasStrategy getAuditAliasStrategy(String busType){
        return this.strategyMap.get(busType);
    }

}