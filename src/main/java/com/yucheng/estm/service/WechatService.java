package com.yucheng.estm.service;

import com.yucheng.estm.entity.OutUser;
import com.yucheng.estm.entity.WechatSend;
import com.yucheng.estm.entity.WechatSendHis;
import com.yucheng.estm.entity.Word;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 微信相关接口
 *
 * @Author liukw 20191019
 */
public interface WechatService {
    /**
     * 外部用户注册
     * @param openId
     * @param name
     * @param phone
     * @return
     */
    OutUser register(String openId, String name, String phone);

    /**
     * 获取所有未推送条目
     * @return
     */
    List<WechatSend> getAllRecond();

    /**
     * 推送逻辑
     * @param wechatSend
     */
    void doSendWechat(WechatSend wechatSend);

    /**
     * 移动现表数据到历史表
     * @param wechatSend
     * @param wechatSendHis
     */
    void move2His(WechatSend wechatSend, WechatSendHis wechatSendHis);

    /**
     * 创建审批单
     * @param outUserId
     * @param busiType
     * @param files
     * @param wordList
     */
    void createAuditOrder(Integer outUserId, int busiType, MultipartFile[] files, List<Word> wordList);
}