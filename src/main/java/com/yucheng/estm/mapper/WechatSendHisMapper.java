package com.yucheng.estm.mapper;

import com.yucheng.estm.entity.WechatSendHis;
import org.springframework.stereotype.Repository;

@Repository
public interface WechatSendHisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatSendHis record);

    int insertSelective(WechatSendHis record);

    WechatSendHis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatSendHis record);

    int updateByPrimaryKey(WechatSendHis record);
}