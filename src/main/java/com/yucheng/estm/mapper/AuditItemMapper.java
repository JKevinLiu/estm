package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.AuditItem;

public interface AuditItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuditItem record);

    int insertSelective(AuditItem record);

    AuditItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuditItem record);

    int updateByPrimaryKey(AuditItem record);
}