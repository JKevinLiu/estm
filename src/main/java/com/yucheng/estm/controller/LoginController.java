package com.yucheng.estm.controller;

import com.yucheng.estm.dto.JsonResult;
import com.yucheng.estm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/dologin")
    public ModelAndView dologin(String username, String password){
        ModelAndView model=new ModelAndView();
        /*User user=new User();
        User loginUser=new User();
        user.setPassword(password);
        user.setUserId(Integer.parseInt(id));
        loginUser=userService.getUserById(user);
        if(null!=loginUser){
            session.setAttribute("user",loginUser);
            model.setViewName("login/index");
        }else {
            model.addObject("MSG","用户名或密码错误");
            model.setViewName("/login/login");
        }*/
        return  model;

    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error";
    }
}
