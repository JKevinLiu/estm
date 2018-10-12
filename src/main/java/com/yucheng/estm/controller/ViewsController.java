package com.yucheng.estm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewsController {

    /**
     * 访问
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }


}