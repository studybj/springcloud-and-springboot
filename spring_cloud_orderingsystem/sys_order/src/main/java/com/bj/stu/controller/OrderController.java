package com.bj.stu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${server.port}")
    private String port;
    @RequestMapping("/index")
    public String index(){
        return "端口是：" + port;
    }
}
