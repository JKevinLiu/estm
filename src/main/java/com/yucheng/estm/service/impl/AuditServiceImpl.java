package com.yucheng.estm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucheng.estm.config.InitCommonContext;
import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.dto.AuditDto;
import com.yucheng.estm.dto.ImgAlais;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.entity.AuditItem;
import com.yucheng.estm.entity.WechatSend;
import com.yucheng.estm.mapper.AuditItemMapper;
import com.yucheng.estm.mapper.AuditMapper;
import com.yucheng.estm.mapper.WechatSendMapper;
import com.yucheng.estm.service.AuditService;
import com.yucheng.estm.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService{
    private static Logger log = Logger.getLogger(AuditServiceImpl.class);

    @Autowired
    private AuditMapper auditMapper;

    @Autowired
    private AuditItemMapper auditItemMapper;

    @Autowired
    private WechatSendMapper wechatSendMapper;

    @Override
    public PageInfo<AuditDto> getAuditByCondtion(int curPage, int pageSize, Audit audit) {
        try{
            PageHelper.startPage(curPage, pageSize);
            List<Audit> auditList = auditMapper.selectListByCondition(audit);

            List<AuditDto> auditDtoList = new ArrayList<>();

            for(Audit auditBo : auditList){
                AuditDto auditDto = new AuditDto();
                auditDto.setOutUserName(auditBo.getOutUser().getName());
                auditDto.setOutPhone(auditBo.getOutUser().getPhone());
                auditDto.setOrderNo(auditBo.getOrderNo());
                auditDto.setAuditDate(DateUtil.formatYYYYMMDDHHmmss(auditBo.getAuditDate()));
                auditDto.setCreateDate(DateUtil.formatYYYYMMDDHHmmss(auditBo.getCreateDate()));
                auditDto.setBusType(InitCommonContext.getCatalogNameMap().get(auditBo.getBusType()));

                int state = auditBo.getState();

                switch (state){
                    case CommonContant.STATE_WAITAUDIT:
                        auditDto.setState(CommonContant.STATE_WAITAUDIT_DESC);
                        break;
                    case CommonContant.STATE_AUDITTING:
                        auditDto.setState(CommonContant.STATE_AUDITTING_DESC);
                        break;
                    case CommonContant.STATE_AUDITPASS:
                        auditDto.setState(CommonContant.STATE_AUDITPASS_DESC);
                        break;
                    case CommonContant.STATE_AUDITBACK:
                        auditDto.setState(CommonContant.STATE_AUDITBACK_DESC);
                        break;
                }

                auditDtoList.add(auditDto);
            }

            return new PageInfo<>(auditDtoList);

        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("查询审核数据异常！");
        }

    }

    @Override
    public Audit getAuditByOrderNo(String orderNo) {
        try {
            return auditMapper.selectByOrderNo(orderNo);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("查询审核数据异常！");
        }
    }

    @Override
    public String getWordItemResource(String orderNo, int itemType) {
        AuditItem item = new AuditItem();
        item.setOrderNo(orderNo);
        item.setItemType(itemType);
        item.setFileType(CommonContant.FT_WORD);
        List<AuditItem> auditItemList = auditItemMapper.selectListByCondition(item);
        if(auditItemList != null && auditItemList.size() == 1){
            AuditItem auditItem = auditItemList.get(0);
            String uri = auditItem.getPath() + auditItem.getFileName();
            return uri;
        }else{
            throw new RuntimeException("你所访问的资源不存在！");
        }
    }

    @Override
    public List<ImgAlais> getImgAliasList(String orderNo) {

        try{
            List<ImgAlais> aliasList = new ArrayList<>();

            ImgAlais cardImgs = new ImgAlais(CommonContant.IT_CARD);
            ImgAlais regResidenceImgs = new ImgAlais(CommonContant.IT_REG_RESIDENCE);
            ImgAlais contractImgs = new ImgAlais(CommonContant.IT_CONTRACT);

            aliasList.add(cardImgs);
            aliasList.add(regResidenceImgs);
            aliasList.add(contractImgs);

            List<String> cardUriList = new ArrayList<>();
            List<String> regResidenceUriList = new ArrayList<>();
            List<String> contractUriList = new ArrayList<>();

            cardImgs.setImgUriList(cardUriList);
            regResidenceImgs.setImgUriList(regResidenceUriList);
            contractImgs.setImgUriList(contractUriList);

            AuditItem item = new AuditItem();
            item.setOrderNo(orderNo);
            item.setFileType(CommonContant.FT_IMG);
            List<AuditItem> auditItemList = auditItemMapper.selectListByCondition(item);

            for(AuditItem auditItem : auditItemList){
                switch (auditItem.getItemType()){
                    case CommonContant.IT_CARD :
                        cardUriList.add(auditItem.getPath() + auditItem.getFileName());
                        break;
                    case CommonContant.IT_REG_RESIDENCE :
                        regResidenceUriList.add(auditItem.getPath() + auditItem.getFileName());
                        break;
                    case CommonContant.IT_CONTRACT :
                        contractUriList.add(auditItem.getPath() + auditItem.getFileName());
                        break;
                }
            }

            return aliasList;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("材料数据异常！");
        }
    }

    @Override
    public void commitAudit(int auditId, boolean isSuccess, String sendDate, String reson) {
        try{
            Audit audit = auditMapper.selectByPrimaryKey(auditId);
            audit.setAuditDate(DateUtil.getCurrentDate());
            audit.setRemark(reson);
            if(isSuccess){
                audit.setState(CommonContant.STATE_AUDITPASS);
            }else{
                audit.setState(CommonContant.STATE_AUDITBACK);
            }

            auditMapper.updateByPrimaryKeySelective(audit);

            //插入推送表
            WechatSend wechatSend = new WechatSend();
            wechatSend.setAuditId(auditId);
            //wechatSend.setOutuserId();
            wechatSend.setCotent(reson);
            wechatSend.setCreateDate(DateUtil.getCurrentDate());
            wechatSend.setSendDate(DateUtil.YYYYMMddmmss2Date(sendDate));
            //wechatSend.setTemplateId();

            wechatSendMapper.insert(wechatSend);

        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("提交异常！");
        }
    }

}