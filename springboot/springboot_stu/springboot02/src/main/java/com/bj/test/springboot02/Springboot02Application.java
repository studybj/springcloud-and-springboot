package com.bj.test.springboot02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Springboot02Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02Application.class, args);
	}
}
