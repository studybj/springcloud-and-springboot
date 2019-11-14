package com.bj.study.springboot.config;

import com.bj.study.springboot.commont.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class myconfig {
    @Bean
    public LocaleResolver myLocaleResolver(){
        return new MyLocaleResolver();
    }
}
