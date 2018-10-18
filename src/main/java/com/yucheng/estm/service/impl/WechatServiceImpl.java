package com.yucheng.estm.service.impl;

import com.yucheng.estm.entity.*;
import com.yucheng.estm.mapper.*;
import com.yucheng.estm.service.WechatService;
import com.yucheng.estm.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatServiceImpl implements WechatService {
    private static Logger log = Logger.getLogger(WechatServiceImpl.class);

    @Autowired
    private OutUserMapper outUserMapper;

    @Autowired
    private AuditMapper auditMapper;

    @Autowired
    private AuditItemMapper auditItemMapper;

    @Autowired
    private ReqCertMapper reqCertMapper;

    @Autowired
    private MarriageMapper marriageMapper;

    @Autowired
    private RecognizanceMapper recognizanceMapper;

    @Autowired
    private WechatSendMapper wechatSendMapper;

    @Autowired
    private WechatSendHisMapper wechatSendHisMapper;



    @Override
    public OutUser register(String openId, String name, String phone) {
        try {
            OutUser newUser = new OutUser();
            newUser.setName(name);
            newUser.setOpenId(openId);
            newUser.setPhone(phone);
            newUser.setCreateDate(DateUtil.getCurrentDate());
            outUserMapper.insert(newUser);
            return newUser;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("用户注册失败！");
        }

    }

    @Override
    public void createAuditOrder(Audit audit, List<AuditItem> auditItemList, ReqCert reqCert, Marriage marriage, Recognizance recognizance) {
        try{
            auditMapper.insert(audit);
            auditItemMapper.insertBatch(auditItemList);
            reqCertMapper.insert(reqCert);
            marriageMapper.insert(marriage);
            recognizanceMapper.insert(recognizance);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("创建审核单失败！");
        }
    }

    @Override
    public List<WechatSend> getAllRecond() {
        return wechatSendMapper.selectAll();
    }

    @Override
    public void doSendWechat(WechatSend wechatSend) {
        //推送逻辑 TODO
    }

    @Override
    public void move2His(WechatSend wechatSend, WechatSendHis wechatSendHis) {
        wechatSendMapper.deleteByPrimaryKey(wechatSend.getId());
        wechatSendHisMapper.insert(wechatSendHis);
    }
}