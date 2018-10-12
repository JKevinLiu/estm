package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.Audit;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Audit record);

    int insertSelective(Audit record);

    Audit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Audit record);

    int updateByPrimaryKey(Audit record);
}