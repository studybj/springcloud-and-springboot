package com.bj.pro.qa.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if(!header.isEmpty()){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJwt(token);
                    String roles = (String) claims.get("roles");
                    if(!roles.isEmpty() && roles.contains("admin")){
                        request.setAttribute("claims_admin", token);
                    }
                    if(!roles.isEmpty() && roles.contains("user")){
                        request.setAttribute("claims_user", token);
                    }
                }catch (Exception e){
                    throw new RuntimeException("令牌有误");
                }
            }
        }
        return true;
    }
}
