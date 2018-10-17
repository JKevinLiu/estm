package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.Recognizance;
import org.springframework.stereotype.Repository;

@Repository
public interface RecognizanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recognizance record);

    int insertSelective(Recognizance record);

    Recognizance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recognizance record);

    int updateByPrimaryKey(Recognizance record);
}