package com.bj.pro.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

@SpringBootApplication
//@EnableEurekaClient
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    @Bean   //id生成器注入该容器
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
