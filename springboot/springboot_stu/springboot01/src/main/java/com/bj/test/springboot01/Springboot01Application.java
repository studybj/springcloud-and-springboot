package com.bj.test.springboot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//其中@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用
// @Configuration，@EnableAutoConfiguration和@ComponentScan
@SpringBootApplication
//EnableScheduling
public class Springboot01Application {

	public static void main(String[] args) {

		SpringApplication.run(Springboot01Application.class, args);
	}
}
