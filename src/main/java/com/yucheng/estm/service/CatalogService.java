package com.yucheng.estm.service;

import com.yucheng.estm.entity.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> getCatalogListByParentId(Integer parentId);
}