package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.Marriage;
import org.springframework.stereotype.Repository;

@Repository
public interface MarriageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Marriage record);

    int insertSelective(Marriage record);

    Marriage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Marriage record);

    int updateByPrimaryKey(Marriage record);
}