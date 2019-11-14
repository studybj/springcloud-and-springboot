package com.bj.demo.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class baseController {
    @GetMapping(value = "/")
    public String toLogin(){
        return "login";
    }
    @PostMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model){
        //使用shiro认证
        //1.获取凭证
        Subject subject = SecurityUtils.getSubject();
        //2.封装token
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //提交
        try {
            subject.login(token);
            return "main";
        }catch (UnknownAccountException e){
            model.addAttribute("username", username);
            model.addAttribute("msg","用户名不存在msg");
            model.addAttribute("usernameMsg","用户名不存在");
            return "login";
        }catch (Exception e){
            model.addAttribute("username", username);
            model.addAttribute("msg","密码有误msg");
            model.addAttribute("passwordMsg","密码有误");
            return "login";
        }
    }

    @GetMapping(value = "/add")
    public String add(){
        return "/user/add";
    }
    @GetMapping(value = "/update")
    public String update(){
        return "/user/update";
    }
    @GetMapping(value = "/unauth")
    public String unauth(){
        return "unauth";
    }
    @GetMapping(value = "/dd")
    public String dd(){
        return "dd";
    }
}
