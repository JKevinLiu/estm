package com.yucheng.estm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ManagerController {

    /**
     * 登录成功后跳转到此界面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpSession session){
        if(session.getAttribute("curUser") == null){
            return "login";
        }
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("audit_manager")
    public String auditManager(){
        return "html/audit_manager";
    }


}