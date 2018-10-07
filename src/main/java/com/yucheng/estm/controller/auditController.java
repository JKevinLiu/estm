package com.yucheng.estm.controller;

import com.yucheng.estm.dto.EasyUIResult;
import com.yucheng.estm.entity.Audit;
import com.yucheng.estm.utils.AuditUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/audit")
public class auditController {

    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> getAuditList (){
        EasyUIResult ret = new EasyUIResult();
        try {
            Map retMap = new HashMap();
            List<Audit> auditList = AuditUtil.getAuditList();
            retMap.put("rows",auditList);
            retMap.put("total",2);
            ret.setTotal(2);
            ret.setRows(auditList);
        } catch (Exception e) {
            //r.setResult(e.getClass().getName() + ":" + e.getMessage());
            //r.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(ret);
    }
}
