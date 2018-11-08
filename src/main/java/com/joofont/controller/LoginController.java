package com.joofont.controller;

import com.joofont.entity.Manage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cui jun on 2018/11/6.
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/v1/admin")
    public String login(Manage manage, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(manage.getName(), manage.getPassword());
        try {
            subject.login(token);
            return "admin";
        } catch (Exception e){
            model.addAttribute("error","用户名或密码错误");
            return "login";
        }
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/student")
    public String student(){
        return "admin";
    }

    @RequestMapping("/teacher")
    public String teacher(){
        return "admin";
    }

}
