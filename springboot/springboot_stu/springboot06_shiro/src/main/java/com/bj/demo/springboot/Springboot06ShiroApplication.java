package com.bj.demo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bj.demo.springboot.dao")
public class Springboot06ShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot06ShiroApplication.class, args);
	}

}
