package com.bj.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Springboot02ThymeleafApplication /*extends SpringBootServletInitializer*/ {
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Springboot02ThymeleafApplication.class);
	}*/
	public static void main(String[] args) {
		SpringApplication.run(Springboot02ThymeleafApplication.class, args);
	}

}

