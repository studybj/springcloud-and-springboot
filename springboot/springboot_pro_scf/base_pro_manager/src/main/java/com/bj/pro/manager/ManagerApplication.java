package com.bj.pro.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import utils.JwtUtil;

@SpringBootApplication
@EnableEurekaClient //以上注解可通过@SpringCloudApplication来标注
@EnableZuulProxy
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
