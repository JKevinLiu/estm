package com.yucheng.estm.controller;

import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.dto.LoginDto;
import com.yucheng.estm.entity.InnerUser;
import com.yucheng.estm.service.InnerUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private InnerUserService innerUserService;
    /**
     * 登录
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public ResponseEntity<JsonResult> dologin(@RequestBody LoginDto loginDto){
        JsonResult r = new JsonResult();
        try {
            InnerUser loginUser = innerUserService.dologinUser(loginDto.getUsername(), loginDto.getPassword());
            r.setResult(loginUser);
            r.setDesc("登录成功");
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
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<JsonResult> logout(){
        JsonResult r = new JsonResult();
        try {
            //demo不做实现
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }



}