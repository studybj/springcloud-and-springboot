package com.bj.study.springboot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.ClassUtils;

@SpringBootApplication
public class Springboot04Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return  application.sources(Springboot04Application.class);
	}
	public static void main(String[] args) {
		String path1 =  ClassUtils.getDefaultClassLoader().getResource("").getPath();
		System.out.println("----------------------------------------");
		System.out.println(path1);
		System.out.println("-----------------------------");
		SpringApplication.run(Springboot04Application.class, args);
	}
}
