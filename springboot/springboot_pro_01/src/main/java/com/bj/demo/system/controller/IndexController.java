package com.bj.demo.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        System.out.println("------------------------index");
        return "index";
    }
    @RequestMapping("/tologin")
    public String tologin(){
        System.out.println("------------------------tologin");
        return "common/login";
    }
    @PostMapping(value = "/login")
    public String login(){
        //userService.l
        System.out.println("------------------------dengl");
        return null;
    }
}
