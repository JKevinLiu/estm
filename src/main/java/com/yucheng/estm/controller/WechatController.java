package com.yucheng.estm.controller;

import com.yucheng.estm.constants.MessageContant;
import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.vo.RegisterVo;
import com.yucheng.estm.entity.OutUser;
import com.yucheng.estm.entity.Word;
import com.yucheng.estm.service.OutUserService;
import com.yucheng.estm.service.WechatService;
import com.yucheng.estm.vo.ApplyVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信请求控制器
 *
 * @Author liukw 20191019
 */
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
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> register(@RequestBody RegisterVo registerDto){
        JsonResult r = new JsonResult();

        try {
            //接入校验 TODO

            OutUser outUser = wechatService.register(registerDto.getOpenId(), registerDto.getName(), registerDto.getPhone());
            r.setDesc("注册成功！");
            r.setResult(outUser);
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
    public ResponseEntity<JsonResult> create(@RequestParam("imgs") MultipartFile[] files, ApplyVo applyVo){

        JsonResult r = new JsonResult();

        Word reqCert = applyVo.getReqCert();
        Word marriage = applyVo.getMarriage();
        Word recognizance = applyVo.getRecognizance();

        List<Word> wordList = new ArrayList<>();

        wordList.add(reqCert);
        wordList.add(marriage);
        wordList.add(recognizance);

        try {
            wechatService.createAuditOrder(applyVo.getOutUserId(), applyVo.getBusiType(), files, wordList);
            r.setDesc("提交成功！");
            r.setStatus(MessageContant.STATUS_OK);
        } catch (Exception e) {
            r.setStatus(MessageContant.STATUS_FAIL);
            r.setDesc(e.getMessage());
        }
        return ResponseEntity.ok(r);
    }

}