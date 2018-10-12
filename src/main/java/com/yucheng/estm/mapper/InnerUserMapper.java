package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.InnerUser;
import org.springframework.stereotype.Repository;

@Repository
public interface InnerUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InnerUser record);

    int insertSelective(InnerUser record);

    InnerUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InnerUser record);

    int updateByPrimaryKey(InnerUser record);
}