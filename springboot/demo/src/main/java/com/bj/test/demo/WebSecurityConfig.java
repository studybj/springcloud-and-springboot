package com.bj.test.demo;

import com.bj.test.demo.filter.ValidateCodefilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration    //指明该类是一个配置类
@EnableWebSecurity  //开启 Security 服务
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启全局 Securtiy 注解,开启方法权限控制
@Slf4j
// 核心配置，配置SpringSecurity访问策略，包括登录处理，登出处理，资源访问，密码基本加密。
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ValidateCodefilter validateCodefilter;
    //配置策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // post请求默认的都开启了csrf的模式，所有post请求都必须带有token之类的验证信息才可以进入登陆页面，此处是禁用csrf模式
        http.csrf().disable();
        http.addFilterBefore(validateCodefilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated();
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//        auth.eraseCredentials(false);
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //密码加密
        return new BCryptPasswordEncoder(4);
    }
}
