package com.bj.study.controller;

import com.bj.study.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private User user;
    @Value("${com.bj.info}")
    private String info;

    @Value("${com.bj.random}")
    private String random;
    @RequestMapping(value = "/userinfo")
    public  String userInfo(){
        return "实体对象信息:"+user.getName()+","+user.getSex();
    }
    @RequestMapping(value = "/userinfo2")
    public  String userInfo2(){
        return "参数引用信息:"+info;
    }
    @RequestMapping(value = "/random")
    public  String random(){
        return "随机值信息:"+random;
    }

    @Value("${com.bj.test.name}")
    private String name;
    @Value("${com.bj.test.sex}")
    private String sex;
    @RequestMapping(value = "/privateinfo")
    public  String privateInfo(){
        return "自定义文件信息:"+name+","+sex;
    }

}