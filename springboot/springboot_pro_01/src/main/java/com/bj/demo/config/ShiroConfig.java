package com.bj.demo.config;

import com.bj.demo.common.MyOwnRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    //注入自定义的realm
    @Bean
    public MyOwnRealm myOwnRealm(){
        return  new MyOwnRealm();
    }
    //将realm注入到SecurityManager中
    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(myOwnRealm());
        return  securityManager;
    }
    //将SecurityManager注入到工厂中
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置一些拦截

        return shiroFilterFactoryBean;
    }
}
