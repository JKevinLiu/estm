package com.yucheng.estm.controller;

import com.github.pagehelper.PageInfo;
import com.yucheng.estm.constants.CommonContant;
import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.dto.manager.DataImage;
import com.yucheng.estm.entity.AliasAudit;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.service.AuditService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/audit")
public class AuditController {
    private static Logger log = Logger.getLogger(AuditController.class);

    @Autowired
    private AuditService auditService;





    /**
     * 生成审核单
     */
    @RequestMapping(value = "/create")
    public ResponseEntity<JsonResult> create(String outUserId, Map<String, String> reqCertWord, Map<String, String> marriageWord,
                                             Map<String, String> recogWord, List<DataImage> dataImageList){
        JsonResult r = new JsonResult();

        try {
            Audit audit = auditService.createAuditOrder(Integer.parseInt(outUserId), reqCertWord, marriageWord, recogWord, dataImageList);

            r.setResult(audit);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

    /**
     *审核单列表，分页，条件过滤
     */
    @RequestMapping(value = "/list")
    public ResponseEntity<JsonResult> list(int curPage, int pageSize, Audit audit){
        JsonResult r = new JsonResult();
        try {
            PageInfo<Audit> pageInfo = auditService.getAuditByCondtion(curPage, pageSize, audit);

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
    @RequestMapping(value = "/doAudit")
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
            }else if(state == CommonContant.STATE_AUDITPASS && state == CommonContant.STATE_AUDITBACK){
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
     *审核单明细条目
     */
    @RequestMapping(value = "/detail")
    public ResponseEntity<JsonResult> auditDetail(String orderNo){
        JsonResult r = new JsonResult();
        try {
            List<AliasAudit> aliasList = auditService.auditAliasInfo(orderNo);
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
    public ResponseEntity<JsonResult> commit(int auditId){
        JsonResult r = new JsonResult();
        try {
            //PageInfo<Order> pageInfo = orderService.getOrderList(reqPage);
            //r.setResult(user);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

}