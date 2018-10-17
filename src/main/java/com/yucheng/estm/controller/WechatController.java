package com.yucheng.estm.controller;

import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.entity.*;
import com.yucheng.estm.service.OutUserService;
import com.yucheng.estm.service.WechatService;
import com.yucheng.estm.utils.FileUtil;
import com.yucheng.estm.utils.MapHelper;
import com.yucheng.estm.utils.OrderUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("/wechat")
public class WechatController {
    private static Logger log = Logger.getLogger(WechatController.class);

    @Autowired
    private WechatService wechatService;

    @Autowired
    private OutUserService outUserService;


    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register")
    public ResponseEntity<JsonResult> register(HttpServletRequest request, HttpServletResponse response){
        JsonResult r = new JsonResult();

        System.out.println("success");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        String openId = request.getParameter("openId");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        try {
            wechatService.register(openId, name, phone);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }


    /**
     * 生成审核单
     */
    @RequestMapping(value = "/create_audit",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> create(Integer outUserId, MultipartFile[] files,
                                             ReqCert reqCert, Marriage marriage, Recognizance recognizance){

        JsonResult r = new JsonResult();

        try {
            OutUser outUser = outUserService.getUserById(outUserId);
            String orderNo =  OrderUtil.createOrderId(outUser);
            String basePath = "audit"+ File.separator + orderNo + File.separator;
            String cardPath = basePath + "card" + File.separator;
            String regResidencePath = basePath + "regResidence" + File.separator;
            String contractPath = basePath + "contract" + File.separator;

            List<AuditItem> auditItemList = new ArrayList<>();

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

                file.transferTo(savefile);

                auditItemList.add(item);
            }

            //不动产申请书
            String reqCertTemplatePath = CommonContant.reqQertWordTemplatePath;
            final String reqCertRealPath = basePath + "reqcert.doc";
            final String reqCertHtmlRealPath = basePath + "reqcert.html";
            final String reqCertImagePath = basePath + File.separator + "image_reqcert" + File.separator;

            AuditItem reqCertItem = new AuditItem();
            reqCertItem.setOrderNo(orderNo);
            reqCertItem.setFileType(CommonContant.FT_WORD);
            reqCertItem.setFileName("reqcert.doc");
            reqCertItem.setItemType(CommonContant.IT_REQ_CERT);
            reqCertItem.setPath(basePath);

            auditItemList.add(reqCertItem);

            //婚姻承状况诺书
            String marriageTemplatePath = CommonContant.marriageWordTemplatePath;
            final String marriageRealPath = basePath + "marriage.doc";
            final String marriageHtmlRealPath = basePath + "marriage.html";
            final String marriageImagePath = basePath + File.separator + "image_marriage" + File.separator;

            AuditItem marriageItem = new AuditItem();
            marriageItem.setOrderNo(orderNo);
            marriageItem.setFileType(CommonContant.FT_WORD);
            marriageItem.setFileName("marriage.doc");
            marriageItem.setItemType(CommonContant.IT_MARRIAGE);
            marriageItem.setPath(basePath);

            auditItemList.add(marriageItem);

            //具结保证书
            String recogTemplatePath = CommonContant.recogWordTemplatePath;
            final String recogRealPath = basePath + "recog.doc";
            final String recogHtmlRealPath = basePath + "recog.html";
            final String recogImagePath = basePath + File.separator + "image_recog" + File.separator;

            AuditItem recogItem = new AuditItem();
            recogItem.setOrderNo(orderNo);
            recogItem.setFileType(CommonContant.FT_WORD);
            recogItem.setFileName("recog.doc");
            recogItem.setItemType(CommonContant.IT_RECOG);
            recogItem.setPath(basePath);

            auditItemList.add(recogItem);

            //生成不动产申请书doc
            File reqCertTemplate = ResourceUtils.getFile(reqCertTemplatePath);
            FileUtil.createDocByTemplate(reqCertRealPath, reqCertTemplate, MapHelper.conver2Map(reqCert));

            //婚姻承状况诺书doc
            File marriageTemplate = ResourceUtils.getFile(marriageRealPath);
            FileUtil.createDocByTemplate(marriageTemplatePath, marriageTemplate, MapHelper.conver2Map(marriage));

            //具结保证书doc
            File recogTemplate = ResourceUtils.getFile(recogRealPath);
            FileUtil.createDocByTemplate(recogTemplatePath, recogTemplate, MapHelper.conver2Map(recognizance));

            //（异步实现 word转换成html）
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //生成不动产申请书html
                        FileUtil.docToHtml(reqCertRealPath, reqCertHtmlRealPath, reqCertImagePath);
                        //婚姻承状况诺书html
                        FileUtil.docToHtml(marriageRealPath, marriageHtmlRealPath, marriageImagePath);
                        //具结保证书html
                        FileUtil.docToHtml(recogRealPath, recogHtmlRealPath, recogImagePath);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }


                }
            }).start();

            Audit audit = new Audit();
            audit.setOrderNo(orderNo);
            audit.setOutUser(outUser);
            audit.setState(CommonContant.STATE_WAITAUDIT);
            audit.setDirPath(basePath);
            audit.setBusType(1);
            audit.setCreateDate(Calendar.getInstance().getTime());

            wechatService.createAuditOrder(audit, auditItemList, reqCert, marriage, recognizance);

            r.setDesc("提交成功！");
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

}