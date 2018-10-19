package com.yucheng.estm.service;

import com.yucheng.estm.entity.Catalog;

import java.util.List;

/**
 * 目录树接口
 *
 * @Author liukw 20191019
 */
public interface CatalogService {
    /**
     * 根据父ID查找子节点目录列表
     * @param parentId
     * @return
     */
    List<Catalog> getCatalogListByParentId(Integer parentId);
}