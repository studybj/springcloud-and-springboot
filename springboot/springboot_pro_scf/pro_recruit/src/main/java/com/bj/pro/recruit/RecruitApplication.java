package com.bj.pro.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

@SpringBootApplication
@EnableEurekaClient
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class, args);
    }

    @Bean   //id生成器注入该容器
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
