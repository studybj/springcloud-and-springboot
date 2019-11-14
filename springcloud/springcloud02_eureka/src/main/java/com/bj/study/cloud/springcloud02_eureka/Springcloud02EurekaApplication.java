package com.bj.study.cloud.springcloud02_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Springcloud02EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud02EurekaApplication.class, args);
	}
}
