package com.bj.pro.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests是所有security注解的开端，表示开始说明需要的权限，
        // 分为两部分：第一部分是拦截的路径，第二部分是访问该路径需要的权限，
        // antMatchers表示拦截的路径，permitAll任何权限都可以访问
        //anyRequest任何请求，authenticated表示需要认证通过后才可访问
        //and().csrf().disable();固定写法，表示csrf(网络攻击)拦截失效
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
