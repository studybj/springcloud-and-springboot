package com.bj.study.cloud.springcloud02_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Springcloud02ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springcloud02ProviderApplication.class, args);
	}
}
