package com.bj.test.springboot01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController的意思就是controller里面的方法都以json格式输出
public class FirstController {
    //第一种，直接访问
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/hellobushu", method = RequestMethod.GET)
    public String hellobushu() {
        return "hellorebushu";
    }

    @GetMapping("/hello1")
    public String hello1() {
        return "hello1";
    }

    //第二种 读取配置文件的参数访问
    @Value("${name}")
    private String name;
    @Value("${age}")
    private int age;
    @Value("${user.name}")
    private String userName;//读取到的是电脑名
    @Value("${user.username}")
    private String userName2;//此处读取中文为乱码，没有找到解决方案

    @GetMapping("/config")
    public String hello2() {
        System.out.println(userName2);
        return "hello1,name:" + name + ",age:" + age + ",userName:" + userName + ",userName2中文2:" + userName2;
    }
}
