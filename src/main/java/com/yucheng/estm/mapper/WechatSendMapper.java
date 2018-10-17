package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.WechatSend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WechatSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatSend record);

    int insertSelective(WechatSend record);

    WechatSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatSend record);

    int updateByPrimaryKey(WechatSend record);

    List<WechatSend> selectAll();
}