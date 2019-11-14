package com.bj.study;

import com.bj.study.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
@EnableConfigurationProperties({User.class})
public class Springboot01Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01Application.class, args);
	}

	@RequestMapping(value = "/", produces = "text/plain;charset=UTF-8")
	String index() {
		return "Hello Spring Boot!";
	}
}