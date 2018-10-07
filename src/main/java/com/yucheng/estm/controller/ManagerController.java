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
    @RequestMapping("wait_audit")
    public String waitAudit(){
        return "html/audit/wait_audit";
    }

    @RequestMapping("finished_audit")
    public String finishedAudit(){
        return "html/audit/wait_audit";
    }

    @RequestMapping("back_audit")
    public String backAudit(){
        return "html/audit/wait_audit";
    }

    @RequestMapping("audit_info")
    public String auditInfo(){
        return "html/audit/audit_info";
    }
}