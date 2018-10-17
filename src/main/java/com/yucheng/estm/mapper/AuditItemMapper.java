package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.AuditItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuditItem record);

    int insertSelective(AuditItem record);

    void insertBatch(List<AuditItem> auditItemList);

    AuditItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuditItem record);

    int updateByPrimaryKey(AuditItem record);

    List<AuditItem> selectListByCondition(AuditItem item);
}