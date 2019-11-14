package com.bj.study.cloud.springcloud02_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Springcloud02ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud02ConsumerApplication.class, args);
	}
}
