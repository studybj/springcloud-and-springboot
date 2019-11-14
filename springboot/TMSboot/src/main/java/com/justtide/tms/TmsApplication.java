package com.justtide.tms;

import com.justtide.tms.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmsApplication.class, args);
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
}
