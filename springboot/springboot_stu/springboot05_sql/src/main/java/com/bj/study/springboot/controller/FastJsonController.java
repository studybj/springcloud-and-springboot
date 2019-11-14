package com.bj.study.springboot.controller;

import com.bj.study.springboot.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("fastjson")
public class FastJsonController {
    @RequestMapping("/test")
    @ResponseBody
    public Role test() {
        Role role = new Role();
        role.setId("roleid01");
        role.setName("jack");
        role.setAvailable("1");
        role.setCreateDate(new Date());
        int i = 1/0;
        return role;
    }
}
