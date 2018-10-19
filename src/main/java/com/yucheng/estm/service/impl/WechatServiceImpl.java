package com.yucheng.estm.service.impl;

import com.yucheng.estm.config.AuditAliasStrategyFactory;
import com.yucheng.estm.entity.*;
import com.yucheng.estm.mapper.*;
import com.yucheng.estm.service.AuditBuisnessStrategy;
import com.yucheng.estm.service.OutUserService;
import com.yucheng.estm.service.WechatService;
import com.yucheng.estm.utils.DateUtil;
import com.yucheng.estm.utils.OrderUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class WechatServiceImpl implements WechatService {
    private static Logger log = Logger.getLogger(WechatServiceImpl.class);

    @Autowired
    private AuditAliasStrategyFactory auditAliasStrategyFactory;

    @Autowired
    private OutUserMapper outUserMapper;

    @Autowired
    private WechatSendMapper wechatSendMapper;

    @Autowired
    private WechatSendHisMapper wechatSendHisMapper;

    @Autowired
    private OutUserService outUserService;



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
    public void createAuditOrder(Integer outUserId, int busiType, MultipartFile[] files, List<Word> wordList) {
        try{

            AuditBuisnessStrategy buisnessStrategy = auditAliasStrategyFactory.getAuditAliasStrategy(busiType);

            OutUser outUser = outUserService.getUserById(outUserId);
            String orderNo =  OrderUtil.createOrderId(outUser);

            buisnessStrategy.setBusiType(busiType);
            buisnessStrategy.createAuditOrder(outUser, orderNo, files, wordList);


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