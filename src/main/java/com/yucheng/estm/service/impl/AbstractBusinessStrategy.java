package com.yucheng.estm.service.impl;

import com.yucheng.estm.service.AuditBuisnessStrategy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public abstract class AbstractBusinessStrategy implements AuditBuisnessStrategy, InitializingBean{
    protected int busiType;

    private static ExecutorService exec;

    @Override
    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        exec = Executors.newFixedThreadPool(3);
    }

    public static ExecutorService getExec() {
        return exec;
    }
}