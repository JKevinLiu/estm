package com.yucheng.estm.controller;

import com.yucheng.estm.entity.Navinfo;
import com.yucheng.estm.entity.User;
import com.yucheng.estm.service.UserService;
import com.yucheng.estm.utils.PremissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 访问登录界面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * 登录请求处理
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/dologin")
    public ModelAndView dologin(String username, String password){
        ModelAndView model=new ModelAndView();

        User loginUser = userService.findUserByUsername(username);
        if(loginUser == null) {
            model.addObject("err_msg","用户名不存在");
            model.setViewName("login");
            return model;
        }

        if(!password.equalsIgnoreCase(loginUser.getPassword())){
            model.addObject("err_msg","密码不正确");
            model.setViewName("login");
            return model;
        }

        model.addObject("curUser", loginUser);

        List<Navinfo> navList = PremissionUtil.getNavList();
        model.addObject("navList", navList);

        model.setViewName("index");

        return  model;
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }

}