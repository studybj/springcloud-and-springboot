package com.bj.demo.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.bj.demo.springboot.shiro.AuthRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        /**
         * 添加shiro内置过滤器，拦截一些请求，可以实现权限相关的拦截
         * 常用的内置过滤有：
         *      anon：无需认证(登录)即可访问；      authc：必须认证才可以访问
         *      user：表示若使用rememberMe功能则可直接访问
         *      perms：表示该资源必须经过授权才可访问
         *      role：表示该资源必须具有指定角色才可访问
         */
        Map filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/login","anon");//第一个参数为路径，第二个参数为权限
        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");


        filterMap.put("/*","authc");    //注意顺序，该句一般放在最后
        factoryBean.setFilterChainDefinitionMap(filterMap);
        factoryBean.setLoginUrl("/");
        factoryBean.setUnauthorizedUrl("/unauth");//没有授权提示页面
        return factoryBean;
    }


    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("authRealm")AuthRealm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "authRealm")
    public AuthRealm getRealm(){
        return new AuthRealm();
    }

    /**
     * 配置ShiroDialect
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
