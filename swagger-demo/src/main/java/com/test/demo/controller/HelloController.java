package com.test.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试 API")
@RequestMapping("/")
public class HelloController {
    @ApiOperation("测试方法")
    @GetMapping
    public String hello(){
        return "dddd";
    }
}
