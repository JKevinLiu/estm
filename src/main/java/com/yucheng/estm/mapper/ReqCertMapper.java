package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.ReqCert;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReqCert record);

    int insertSelective(ReqCert record);

    ReqCert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReqCert record);

    int updateByPrimaryKey(ReqCert record);
}