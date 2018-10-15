package com.yucheng.estm.controller;

import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.dto.JsonResult;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private static Logger log = Logger.getLogger(LoginController.class);

    /**
     * 登录
     */
    @RequestMapping(value = "/dologin")
    public ResponseEntity<JsonResult> dologin(String username, String password){
        JsonResult r = new JsonResult();
        try {
            //r.setResult(user);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout")
    public ResponseEntity<JsonResult> logout(){
        JsonResult r = new JsonResult();
        try {
            //r.setResult(user);
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }



}