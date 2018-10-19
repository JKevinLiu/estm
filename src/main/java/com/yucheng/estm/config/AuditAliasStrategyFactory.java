package com.yucheng.estm.config;

import com.yucheng.estm.service.AuditBuisnessStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 业务类型策略
 *
 * @Author liukw 20191019
 */
@Component
public class AuditAliasStrategyFactory {
    private final static Logger logger = LoggerFactory.getLogger(AuditAliasStrategyFactory.class);

    @Autowired
    private final Map<String, AuditBuisnessStrategy> strategyMap = new ConcurrentHashMap<>();

    public AuditBuisnessStrategy getAuditAliasStrategy(int busType){
        String strategyType = InitCommonContext.getBusinessStrategyMap().get(busType);
        return this.strategyMap.get(strategyType);
    }

}