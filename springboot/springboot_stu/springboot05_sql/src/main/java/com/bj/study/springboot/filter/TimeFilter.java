package com.bj.study.springboot.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------初始化过滤器----------");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("filter 耗时：" + (System.currentTimeMillis() - startTime));
    }
    @Override
    public void destroy() {
        System.out.println("--------销毁过滤器----------");
    }
}
