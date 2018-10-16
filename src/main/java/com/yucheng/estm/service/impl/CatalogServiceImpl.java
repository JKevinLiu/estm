package com.yucheng.estm.service.impl;

import com.yucheng.estm.entity.Catalog;
import com.yucheng.estm.mapper.CatalogMapper;
import com.yucheng.estm.service.CatalogService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    @Override
    public List<Catalog> getCatalogListByParentId(Integer parentId){
        List<Catalog> catalogList = catalogMapper.selectChildListByParentId(parentId);
        return catalogList;
    }


}