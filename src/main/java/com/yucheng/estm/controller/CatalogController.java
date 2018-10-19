package com.yucheng.estm.controller;

import com.yucheng.estm.config.InitCommonContext;
import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.entity.Catalog;
import com.yucheng.estm.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 目录控制器
 *
 * @Author liukw 20191019
 */
@RestController
@RequestMapping("catalog")
public class CatalogController {

    /**
     * 目录树 (demo阶段不用动态生成)
     */
    @RequestMapping(value = "/busi")
    public ResponseEntity<JsonResult> create(){
        JsonResult r = new JsonResult();

        try {
            Catalog root = InitCommonContext.getRoot();

            r.setResult(root);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }
}