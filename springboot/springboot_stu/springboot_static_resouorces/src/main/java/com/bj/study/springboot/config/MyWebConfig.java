package com.bj.study.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置自定义静态资源映射路径，即对外暴露的访问路径
        registry.addResourceHandler("/bj/**")
        //配置自定义静态资源存储目录，可为项目中路径("classpath:/bj/")或外部路径("file:e:/my/")
                .addResourceLocations("classpath:/bj/");
        super.addResourceHandlers(registry);
    }
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/tologin").setViewName("login");
    }
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor()
        super.addInterceptors(registry);
    }
}