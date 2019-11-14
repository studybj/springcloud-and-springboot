package com.bj.demo.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){//该方式使得默认的数据源失效，使用指定的druid数据源
        return new DruidDataSource();
    }
    //配置druid的监控，1.配置一个管理后台的Servlet 2.配置监控的filter
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map map = new HashMap<String,String>();
        map.put("loginUsername","admin");   //后台监控登录名
        map.put("loginPassword","123456"); //后台监控没密码
        map.put("allow",""); //IP白名单指定允许访问的，默认就是允许所有访问
        map.put("deny",""); //IP黑名单拒绝访问的
        bean.setInitParameters(map);
        return bean;
    }
    @Bean
    public FilterRegistrationBean webStatFilter(){//拦截请求
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map map = new HashMap<String,String>();
        map.put("exclusions","*.js,*.css,*.jpg");

        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
