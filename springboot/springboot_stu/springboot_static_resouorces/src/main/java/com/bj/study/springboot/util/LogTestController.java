package com.bj.study.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/logtest")
    public String logTest(){
        System.out.println("进入LogTestController日志控制中心----》》》");
        logger.trace("日志输出LogTestController：trace[所有痕迹]");
        logger.debug("日志输出LogTestController：debug[debug调试]");
        logger.info("日志输出LogTestController：info[信息级别]");
        logger.warn("日志输出LogTestController：warn[警告级别]");
        logger.error("日志输出LogTestController：error[错误级别]");
        return "logTest";
    }
}
