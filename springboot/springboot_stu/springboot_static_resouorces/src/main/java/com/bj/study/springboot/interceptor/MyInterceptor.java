package com.bj.study.springboot.interceptor;

import com.bj.study.springboot.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag =true;
        User user=(User)request.getSession().getAttribute("user");
        if(null==user){
            response.sendRedirect("toLogin");
            flag = false;
        }else{
            flag = true;
        }
        return flag;
    }
}
