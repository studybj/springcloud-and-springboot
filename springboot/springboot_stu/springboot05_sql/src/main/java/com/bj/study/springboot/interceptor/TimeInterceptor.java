package com.bj.study.springboot.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("========preHandle=========");
        if(request.getRequestURL().indexOf(".html")!=-1){
            return false;
        }
        /*System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());*/
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("========postHandle=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("========afterCompletion=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
        System.out.println(ex);
    }
}
