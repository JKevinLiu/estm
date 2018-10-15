package com.yucheng.estm.service.impl;

import com.yucheng.estm.entity.Catalog;
import com.yucheng.estm.mapper.CatalogMapper;
import com.yucheng.estm.service.CatalogService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService, InitializingBean {

    @Autowired
    private CatalogMapper catalogMapper;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public Catalog getCatalogTree() {
        return null;
        //return catalogMapper.select;
    }
}