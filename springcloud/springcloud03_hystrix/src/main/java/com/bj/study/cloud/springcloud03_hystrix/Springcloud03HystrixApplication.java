package com.bj.study.cloud.springcloud03_hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Springcloud03HystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud03HystrixApplication.class, args);
	}
}
