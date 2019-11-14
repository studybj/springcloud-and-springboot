package com.bj.pro.user.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
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
        boolean flag = false;
        String header = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(header)){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    if (StringUtils.isNotBlank(token)) {
                        Claims claims = jwtUtil.parseJwt(token);
                        flag = true;
                        //刷新token,当快要达到过期时间时刷新

                        String roles = (String) claims.get("roles");
                        if (!roles.isEmpty() && roles.contains("admin")) {
                            request.setAttribute("claims_admin", token);
                        }
                        if (!roles.isEmpty() && roles.contains("user")) {
                            request.setAttribute("claims_user", token);
                        }
                    }
                }catch (Exception e){
                    throw new RuntimeException("令牌有误或已过期,请重新登录");
                }
            }
        }
        if (!flag){
            throw new RuntimeException("请先登录");
        }
        return flag;
    }
}
