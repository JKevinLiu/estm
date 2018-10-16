package com.yucheng.estm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucheng.estm.config.InitCommonContext;
import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.dto.AuditDto;
import com.yucheng.estm.dto.ItemDto;
import com.yucheng.estm.dto.manager.DataImage;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.entity.AuditItem;
import com.yucheng.estm.entity.OutUser;
import com.yucheng.estm.config.AuditAliasStrategyFactory;
import com.yucheng.estm.mapper.AuditItemMapper;
import com.yucheng.estm.mapper.AuditMapper;
import com.yucheng.estm.mapper.OutUserMapper;
import com.yucheng.estm.service.AuditAliasStrategy;
import com.yucheng.estm.service.AuditService;
import com.yucheng.estm.utils.DateUtil;
import com.yucheng.estm.utils.FileUtil;
import com.yucheng.estm.utils.OrderUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class AuditServiceImpl implements AuditService{
    private static Logger log = Logger.getLogger(AuditServiceImpl.class);

    @Autowired
    private OutUserMapper outUserMapper;

    @Autowired
    private AuditMapper auditMapper;

    @Autowired
    private AuditItemMapper auditItemMapper;

    @Autowired
    private AuditAliasStrategyFactory auditAliasStrategyFactory;

    @Override
    public Audit createAuditOrder(int outUserId, Map<String, String> reqCertWord, Map<String, String> marriageWord,
                                  Map<String, String> recogWord, List<DataImage> dataImageList) {
        Audit audit = new Audit();

        OutUser outUser = outUserMapper.selectByPrimaryKey(outUserId);
        String orderNo =  OrderUtil.createOrderId(outUser);
        String basePath = "audit"+ File.separator + orderNo + File.separator;

        //不动产申请书
        String reqCertTemplatePath = CommonContant.reqQertWordTemplatePath;
        String reqCertRealPath = basePath + "reqcert.doc";
        String reqCertHtmlRealPath = basePath + "reqcert.html";
        String reqCertImagePath = basePath + File.separator + "image_reqcert" + File.separator;

        //婚姻承状况诺书
        String marriageTemplatePath = CommonContant.marriageWordTemplatePath;
        String marriageRealPath = basePath + "marriage.doc";
        String marriageHtmlRealPath = basePath + "marriage.html";
        String marriageImagePath = basePath + File.separator + "image_marriage" + File.separator;

        //具结保证书
        String recogTemplatePath = CommonContant.recogWordTemplatePath;
        String recogRealPath = basePath + "recog.doc";
        String recogHtmlRealPath = basePath + "recog.html";
        String recogImagePath = basePath + File.separator + "image_recog" + File.separator;

        try{
            //生成不动产申请书doc
            File reqCertTemplate = ResourceUtils.getFile(reqCertTemplatePath);
            FileUtil.createDocByTemplate(reqCertRealPath, reqCertTemplate, reqCertWord);

            //婚姻承状况诺书doc
            File marriageTemplate = ResourceUtils.getFile(marriageRealPath);
            FileUtil.createDocByTemplate(marriageTemplatePath, marriageTemplate, marriageWord);

            //具结保证书doc
            File recogTemplate = ResourceUtils.getFile(recogRealPath);
            FileUtil.createDocByTemplate(recogTemplatePath, recogTemplate, recogWord);

            //生成图片材料
            //TODO

            audit.setOrderNo(orderNo);
            audit.setOutName(outUser.getName());
            audit.setOutPhone(outUser.getPhone());
            audit.setState(CommonContant.STATE_WAITAUDIT);
            audit.setDirPath(basePath);
            audit.setBusType(1);
            audit.setCreateDate(Calendar.getInstance().getTime());
            auditMapper.insert(audit);

            //（异步实现）
            //生成不动产申请书html
            FileUtil.docToHtml(reqCertRealPath, reqCertHtmlRealPath, reqCertImagePath);
            //婚姻承状况诺书html
            FileUtil.docToHtml(marriageRealPath, marriageHtmlRealPath, marriageImagePath);
            //具结保证书html
            FileUtil.docToHtml(recogRealPath, recogHtmlRealPath, recogImagePath);

        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("创建审核单失败！");
        }
        return audit;
    }

    @Override
    public PageInfo<AuditDto> getAuditByCondtion(int curPage, int pageSize, Audit audit) {
        try{
            PageHelper.startPage(curPage, pageSize);
            List<Audit> auditList = auditMapper.selectListByCondition(audit);

            List<AuditDto> auditDtoList = new ArrayList<>();

            for(Audit auditBo : auditList){
                AuditDto auditDto = new AuditDto();
                auditDto.setOutUserName(auditBo.getOutName());
                auditDto.setOrderNo(auditBo.getOrderNo());
                auditDto.setAuditDate(DateUtil.formatYYYYMMDDHHmmss(auditBo.getAuditDate()));
                auditDto.setCreateDate(DateUtil.formatYYYYMMDDHHmmss(auditBo.getCreateDate()));
                auditDto.setBusType(InitCommonContext.getCatalogNameMap().get(auditBo.getBusType()));
                auditDto.setOutPhone(auditBo.getOutPhone());

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
            throw new RuntimeException("查询audit异常！");
        }

    }

    @Override
    public List<List<ItemDto>> auditAliasInfo(String orderNo) {

        try{
            Audit audit = auditMapper.selectByOrderNo(orderNo);

            //根据busType从工厂中找到对应的策略
            AuditAliasStrategy auditAliasStrategy = auditAliasStrategyFactory.getAuditAliasStrategy(audit.getBusType());

            if(auditAliasStrategy == null){
                throw new Exception("无法找到该业务对应的处理策略！");
            }

            List<AuditItem> itemList = auditItemMapper.selectListByOrderNo(orderNo);

            return auditAliasStrategy.MergeItemForAlias(itemList);

        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("系统异常！");
        }
    }

    @Override
    public Audit getAuditByOrderNo(String orderNo) {
        try {
            return auditMapper.selectByOrderNo(orderNo);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("查询audit异常！");
        }
    }

    @Override
    public void commitAudit(int auditId, List<AuditItem> auditItemList, boolean isSuccess, String sendDate, String reson) {

    }

}