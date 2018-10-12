package com.yucheng.estm.controller;

import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.dto.manager.DataImage;
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
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            log.error(e.getMessage(), e);
        }
        return ResponseEntity.ok(r);
    }

    /**
     *审核单列表，分页，条件过滤
     */
    @RequestMapping(value = "/list")
    public ResponseEntity<JsonResult> list(int state, int curPage, int pageSize){
        JsonResult r = new JsonResult();
        try {
            //PageInfo<Order> pageInfo = orderService.getOrderList(reqPage);
            //r.setResult(user);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            log.error(e.getMessage(), e);
        }
        return ResponseEntity.ok(r);
    }

    /**
     *审核单明细条目
     */
    @RequestMapping(value = "/detail")
    public ResponseEntity<JsonResult> auditDetail(int auditId){
        JsonResult r = new JsonResult();
        try {
            //PageInfo<Order> pageInfo = orderService.getOrderList(reqPage);
            //r.setResult(user);
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            log.error(e.getMessage(), e);
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
            r.setStatus("ok");
        } catch (Exception e) {
            r.setResult(e.getClass().getName() + ":" + e.getMessage());
            r.setStatus("error");
            log.error(e.getMessage(), e);
        }
        return ResponseEntity.ok(r);
    }

}