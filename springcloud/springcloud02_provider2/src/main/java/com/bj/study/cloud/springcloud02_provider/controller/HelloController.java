package com.bj.study.cloud.springcloud02_provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index(@RequestParam String name){
        return "hello2   " + name + ",this is first service";
    }
}
