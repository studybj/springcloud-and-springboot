package com.bj.demo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.bj.demo.springboot.base.dao")
@SpringBootApplication
public class SpringbootProLoginAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProLoginAuthApplication.class, args);
	}

}
