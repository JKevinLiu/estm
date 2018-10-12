package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.OutUser;
import org.springframework.stereotype.Repository;

@Repository
public interface OutUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OutUser record);

    int insertSelective(OutUser record);

    OutUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OutUser record);

    int updateByPrimaryKey(OutUser record);
}