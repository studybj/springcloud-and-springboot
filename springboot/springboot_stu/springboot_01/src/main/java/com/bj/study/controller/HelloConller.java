package com.bj.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloConller{
    @Value("${com.bj.name}")
    private  String name;
    @Value("${com.bj.sex}")
    private  String sex;
    @RequestMapping("/info")
    public String info(){
        return name+","+sex;
    }
}
