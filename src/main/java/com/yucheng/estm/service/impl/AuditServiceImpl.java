package com.yucheng.estm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yucheng.estm.config.InitCommonContext;
import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.dto.manager.DataImage;
import com.yucheng.estm.entity.AliasAudit;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.entity.AuditItem;
import com.yucheng.estm.entity.OutUser;
import com.yucheng.estm.config.AuditAliasStrategyFactory;
import com.yucheng.estm.mapper.AuditItemMapper;
import com.yucheng.estm.mapper.AuditMapper;
import com.yucheng.estm.mapper.OutUserMapper;
import com.yucheng.estm.service.AuditAliasStrategy;
import com.yucheng.estm.service.AuditService;
import com.yucheng.estm.utils.FileUtil;
import com.yucheng.estm.utils.OrderUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
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
    public PageInfo<Audit> getAuditByCondtion(int curPage, int pageSize, Audit audit) {
        try{
            PageHelper.startPage(curPage, pageSize);
            List<Audit> auditList = auditMapper.selectListByCondition(audit);
            PageInfo<Audit> pageInfo = new PageInfo<>(auditList);
            return pageInfo;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException("查询audit异常！");
        }

    }

    @Override
    public List<AliasAudit> auditAliasInfo(String orderNo) {

        List<AliasAudit> aliasList = null;
        try{

            Audit audit = auditMapper.selectByOrderNo(orderNo);
            int busType = audit.getBusType();

            String strategyType = InitCommonContext.getBusTypeMap().get("common");

            //根据busType从工厂中找到对应的策略
            AuditAliasStrategy auditAliasStrategy = auditAliasStrategyFactory.getAuditAliasStrategy(strategyType);

            List<AuditItem> itemList = auditItemMapper.selectListByOrderNo(orderNo);

            aliasList = auditAliasStrategy.MergeItemForAlias(itemList);

            return aliasList;

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

}