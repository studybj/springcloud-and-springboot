package com.bj.demo.springboot.config;

import com.bj.demo.springboot.base.dto.SecurityUser;
import com.bj.demo.springboot.base.entity.SysUser;
import com.bj.demo.springboot.base.service.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity  //开启 Security 服务
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启全局 Securtiy 注解,开启方法权限控制
@Slf4j
// 核心配置，配置SpringSecurity访问策略，包括登录处理，登出处理，资源访问，密码基本加密。
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //
    @Autowired
    CustomUserDetailService userDetailService;
    //配置策略
    /** 放行静态资源 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // post请求默认的都开启了csrf的模式，所有post请求都必须带有token之类的验证信息才可以进入登陆页面，这边是禁用csrf模式
        http.csrf().disable();
        http.authorizeRequests().
                // 所有用户均可访问的资源
                antMatchers("/static/**").permitAll()
                // 任何尚未匹配的URL只需要验证用户即可访问
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
                .and()
                .logout().permitAll().invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler())
                .and().sessionManagement().maximumSessions(10).expiredUrl("/login");
                // 自定义登陆用户名和密码参数，默认为username和password
                //.usernameParameter("username")
                //.passwordParameter("password")
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //密码加密
        return new BCryptPasswordEncoder(4);
    }
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    SecurityUser user = (SecurityUser) authentication.getPrincipal();
                    log.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
                } catch (Exception e) {
                    log.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }
                httpServletResponse.sendRedirect("/login");
            }
        };
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                SysUser userDetails = (SysUser) authentication.getPrincipal();
                log.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
