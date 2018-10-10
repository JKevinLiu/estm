package com.yucheng.estm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ViewsController {

    //@Autowired
    //private UserService userService;

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
        /*ModelAndView model=new ModelAndView();

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

        return  model;*/
        return null;
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }


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
