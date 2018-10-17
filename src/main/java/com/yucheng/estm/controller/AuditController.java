package com.yucheng.estm.controller;

import com.github.pagehelper.PageInfo;
import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.dto.AuditDto;
import com.yucheng.estm.dto.ImgAlais;
import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.service.AuditService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {
    private static Logger log = Logger.getLogger(AuditController.class);

    @Autowired
    private AuditService auditService;

    /**
     *审核单列表，分页，条件过滤
     */
    @RequestMapping(value = "/list")
    public ResponseEntity<JsonResult> list(int curPage, int pageSize, Audit audit){
        JsonResult r = new JsonResult();
        try {
            PageInfo<AuditDto> pageInfo = auditService.getAuditByCondtion(curPage, pageSize, audit);

            r.setResult(pageInfo);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

    /**
     *开始审核校验
     */
    @RequestMapping(value = "/check")
    public ResponseEntity<JsonResult> doAudit(String orderNo){
        JsonResult r = new JsonResult();
        try {
            Audit audit = auditService.getAuditByOrderNo(orderNo);
            int state = audit.getState();
            if(state == CommonContant.STATE_WAITAUDIT){
                r.setResult(true);
                r.setDesc("审核单可以审核");
            }else if(state == CommonContant.STATE_AUDITTING){
                r.setResult(false);
                r.setDesc("审核单正在被审核！");
            }else if(state == CommonContant.STATE_AUDITPASS || state == CommonContant.STATE_AUDITBACK){
                r.setResult(false);
                r.setDesc("审核单已审核完成，请刷新页面！");
            }
            r.setStatus(MessageContant.STATUS_OK);

        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

    /**
     * word审核单条目资源uri
     */
    @RequestMapping(value = "/detail/word/{itemType}")
    public ResponseEntity<JsonResult> auditWordDetail(String orderNo, @PathVariable("itemType") String itemTypeStr){
        JsonResult r = new JsonResult();
        try {
            int itemType;

            if("req_cert".equalsIgnoreCase(itemTypeStr)){
                itemType = CommonContant.IT_REQ_CERT;
            }else if ("marri".equalsIgnoreCase(itemTypeStr)){
                itemType = CommonContant.IT_MARRIAGE;
            }else if ("recog".equalsIgnoreCase(itemTypeStr)){
                itemType = CommonContant.IT_RECOG;
            }else{
                throw new Exception("未知类型的文件请求");
            }

            String uri = auditService.getWordItemResource(orderNo, itemType);

            r.setResult(uri);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 证明材料
     */
    @RequestMapping(value = "/detail/master")
    public ResponseEntity<JsonResult> auditMasterDetail(String orderNo){
        JsonResult r = new JsonResult();
        try {
            List<ImgAlais> aliasList = auditService.getImgAliasList(orderNo);
            r.setResult(aliasList);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

    /**
     *提交审核
     */
    @RequestMapping(value = "/commit")
    public ResponseEntity<JsonResult> commit(int auditId, boolean isSuccess, String sendDate,String reson){
        JsonResult r = new JsonResult();
        try {
            auditService.commitAudit(auditId, isSuccess, sendDate, reson);
            r.setStatus(MessageContant.STATUS_OK);
            r.setDesc("提交成功！");
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

}