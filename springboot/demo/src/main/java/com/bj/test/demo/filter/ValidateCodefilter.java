package com.bj.test.demo.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodefilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri= request.getRequestURI();
        //如果为get请求并且请求uri为/login（也就是我们登录表单的form的action地址）
        if( StringUtils.equalsIgnoreCase(request.getMethod(),"get") && StringUtils.containsIgnoreCase(request.getRequestURI(),"/login")) {
            logger.info("ValidateCodefilter执行了----" + "request.getRequestURI()=" + uri);
            //这里需要验证前端传过来的验证码是否和session里面存的一致，并且要判断是否过期
            logger.info(request.getSession().getAttribute("imageCode"));
            validateCode(request);
        }
    }
    /**
     * 验证用户输入的验证码和session中存的是否一致
     * @param request
     */
    private void validateCode(HttpServletRequest request) {

    }

}
