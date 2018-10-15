package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.Audit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Audit record);

    int insertSelective(Audit record);

    Audit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Audit record);

    int updateByPrimaryKey(Audit record);

    List<Audit> selectListByCondition(Audit audit);

    Audit selectByOrderNo(String orderNo);
}