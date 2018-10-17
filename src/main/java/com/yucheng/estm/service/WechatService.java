package com.yucheng.estm.service;

import com.yucheng.estm.entity.*;

import java.util.List;

public interface WechatService {
    void register(String openId, String name, String phone);

    void createAuditOrder(Audit audit, List<AuditItem> auditItemList, ReqCert reqCert, Marriage marriage, Recognizance recognizance);

    List<WechatSend> getAllRecond();

    void doSendWechat(WechatSend wechatSend);

    void move2His(WechatSend wechatSend, WechatSendHis wechatSendHis);
}