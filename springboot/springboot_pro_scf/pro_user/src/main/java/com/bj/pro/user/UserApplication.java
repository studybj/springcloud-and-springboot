package com.bj.pro.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;
import utils.JwtUtil;

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
