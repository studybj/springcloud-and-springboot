package com.bj.study.cloud.springcloud01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Springcloud01Application {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud01Application.class, args);
	}
}
