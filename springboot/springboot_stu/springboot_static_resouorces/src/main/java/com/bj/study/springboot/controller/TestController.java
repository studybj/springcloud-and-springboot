package com.bj.study.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/index")
    public  String index(){
        System.out.println("进入TestController日志控制中心----》》》");
        logger.trace("日志输出TestController：trace[所有痕迹]");
        logger.debug("日志输出TestController：debug[debug调试]");
        logger.info("日志输出TestController：info[信息级别]");
        logger.warn("日志输出TestController：warn[警告级别]");
        logger.error("日志输出TestController：error[错误级别]");
        return "ddddd";
    }
}
