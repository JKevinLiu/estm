package com.yucheng.estm.service.impl;

import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.entity.*;
import com.yucheng.estm.mapper.*;
import com.yucheng.estm.service.AuditBuisnessStrategy;
import com.yucheng.estm.utils.FileUtil;
import com.yucheng.estm.utils.MapHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("common")
public class CommonBuisnessStrategy extends  AbstractBusinessStrategy implements AuditBuisnessStrategy {
    private static Logger log = Logger.getLogger(CommonBuisnessStrategy.class);

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

    @Override
    public void createAuditOrder(OutUser outUser, String orderNo, MultipartFile[] files, List<Word> wordList) throws Exception {

        Audit audit = new Audit();
        List<AuditItem> auditItemList = new ArrayList<>();


        String basePath = "audit"+ File.separator + orderNo + File.separator;

        audit.setOrderNo(orderNo);
        audit.setOutUser(outUser);
        audit.setState(CommonContant.STATE_WAITAUDIT);
        audit.setDirPath(basePath);
        audit.setBusType(busiType);
        audit.setCreateDate(Calendar.getInstance().getTime());

        auditMapper.insert(audit);

        for(Word word : wordList){
            if( word instanceof  ReqCert){
                reqCertMapper.insert((ReqCert)word);
            }else if(word instanceof Marriage ){
                marriageMapper.insert((Marriage)word);
            }else if(word instanceof Recognizance ){
                recognizanceMapper.insert((Recognizance)word);
            }
        }

        String cardPath = basePath + "card" + File.separator;
        String regResidencePath = basePath + "regResidence" + File.separator;
        String contractPath = basePath + "contract" + File.separator;

        //证明材料
        for (MultipartFile file : files) {
            String fileName = file.getName();
            AuditItem item = new AuditItem();

            item.setOrderNo(orderNo);
            item.setFileType(CommonContant.FT_IMG);
            item.setFileName(fileName);

            if(fileName.contains("CARD")){
                item.setItemType(CommonContant.IT_CARD);
                item.setPath(cardPath);
            }else if(fileName.contains("REG_RESIDENCE")){
                item.setItemType(CommonContant.IT_REG_RESIDENCE);
                item.setPath(regResidencePath);
            }else if(fileName.contains("CONTRACT")){
                item.setItemType(CommonContant.IT_CONTRACT);
                item.setPath(contractPath);
            }

            // 获取上传文件路径
            String uploadPath = item.getPath();
            File savefile = new File(uploadPath + fileName);
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }

            try {
                file.transferTo(savefile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            auditItemList.add(item);
        }


        //不动产申请书
        final String reqCertTemplatePath = CommonContant.reqQertWordTemplatePath;
        final String reqCertRealPath = basePath + "reqcert.doc";
        final String reqCertHtmlRealPath = basePath + "reqcert.html";
        final String reqCertImagePath = basePath + File.separator + "image_reqcert" + File.separator;

        final AuditItem reqCertItem = new AuditItem();
        reqCertItem.setOrderNo(orderNo);
        reqCertItem.setFileType(CommonContant.FT_WORD);
        reqCertItem.setFileName("reqcert.doc");
        reqCertItem.setItemType(CommonContant.IT_REQ_CERT);
        reqCertItem.setPath(basePath);

        auditItemList.add(reqCertItem);

        //婚姻承状况诺书
        final String marriageTemplatePath = CommonContant.marriageWordTemplatePath;
        final String marriageRealPath = basePath + "marriage.doc";
        final String marriageHtmlRealPath = basePath + "marriage.html";
        final String marriageImagePath = basePath + File.separator + "image_marriage" + File.separator;

        final AuditItem marriageItem = new AuditItem();
        marriageItem.setOrderNo(orderNo);
        marriageItem.setFileType(CommonContant.FT_WORD);
        marriageItem.setFileName("marriage.doc");
        marriageItem.setItemType(CommonContant.IT_MARRIAGE);
        marriageItem.setPath(basePath);

        auditItemList.add(marriageItem);

        //具结保证书
        final String recogTemplatePath = CommonContant.recogWordTemplatePath;
        final String recogRealPath = basePath + "recog.doc";
        final String recogHtmlRealPath = basePath + "recog.html";
        final String recogImagePath = basePath + File.separator + "image_recog" + File.separator;

        final AuditItem recogItem = new AuditItem();
        recogItem.setOrderNo(orderNo);
        recogItem.setFileType(CommonContant.FT_WORD);
        recogItem.setFileName("recog.doc");
        recogItem.setItemType(CommonContant.IT_RECOG);
        recogItem.setPath(basePath);

        auditItemList.add(recogItem);

        auditItemMapper.insertBatch(auditItemList);

        for(final Word word : wordList){

            if( word instanceof  ReqCert){
                getExec().execute(new Runnable() {
                    @Override
                    public void run() {

                        ReqCert reqCert = (ReqCert)word;
                        try {
                            reqCert.setAuditItemId(reqCertItem.getId());
                            //生成生成不动产申请书doc
                            File reqCertTemplate = ResourceUtils.getFile(reqCertTemplatePath);
                            FileUtil.createDocByTemplate(reqCertRealPath, reqCertTemplate, MapHelper.conver2Map(reqCert));

                            //生成生成不动产申请书html
                            FileUtil.docToHtml(reqCertRealPath, reqCertHtmlRealPath, reqCertImagePath);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                        reqCertMapper.insert(reqCert);
                    }
                });

            }else if(word instanceof Marriage ){

                getExec().execute(new Runnable() {
                    @Override
                    public void run() {

                        Marriage marriage = (Marriage)word;
                        try {
                            marriage.setAuditItemId(marriageItem.getId());
                            //生成婚姻承状况诺书doc
                            File marriageTemplate = ResourceUtils.getFile(marriageRealPath);
                            FileUtil.createDocByTemplate(marriageTemplatePath, marriageTemplate, MapHelper.conver2Map(marriage));

                            //生成婚姻承状况诺书html
                            FileUtil.docToHtml(marriageRealPath, marriageHtmlRealPath, marriageImagePath);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                        marriageMapper.insert(marriage);
                    }
                });

            }else if(word instanceof Recognizance ){

                getExec().execute(new Runnable() {
                    @Override
                    public void run() {

                        Recognizance recognizance = (Recognizance)word;
                        try {
                            recognizance.setAuditItemId(recogItem.getId());
                            //生成具结保证书doc
                            File recogTemplate = ResourceUtils.getFile(recogRealPath);
                            FileUtil.createDocByTemplate(recogTemplatePath, recogTemplate, MapHelper.conver2Map(recognizance));

                            //生成具结保证书html
                            FileUtil.docToHtml(recogRealPath, recogHtmlRealPath, recogImagePath);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                        recognizanceMapper.insert(recognizance);
                    }
                });
            }
        }
    }
}