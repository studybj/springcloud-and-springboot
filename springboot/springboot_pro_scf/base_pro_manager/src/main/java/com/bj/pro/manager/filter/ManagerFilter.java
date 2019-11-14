package com.bj.pro.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 过滤器的类型
     * pre为执行之前过滤
     * post为执行之后过滤
     * */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
    /**
     * 过滤器的执行顺序，返回值越小，优先级越高
     * */
    @Override
    public int filterOrder() {
        return 0;
    }
    /**
     * 当前过滤器是否开启
     * */
    @Override
    public boolean shouldFilter() {
        return false;
    }
    /**
     * 过滤器的执行操作，return任意值都表示继续执行
     * setSendzuulResponse(false)表示不在继续执行
     * */
    @Override
    public Object run() throws ZuulException {
        //需注意经过网关后，头信息会丢失，若需要头信息，此处需将其转发给对应的服务
        //得到上下文
        RequestContext context = RequestContext.getCurrentContext();
        //得到request
        HttpServletRequest request = context.getRequest();
        //若该请求是网关内的转发请求，则跳出
        if (request.getMethod().equals("OPTIONS")){
            return null;
        }
        //若该请求登录请求
        if (request.getRequestURI().indexOf("login") > -1){
            return null;
        }

        //得到需要的头信息
        String header = request.getHeader("Authorization");
        if(!header.isEmpty()){
            //网关验证权限
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJwt(token);
                    String roles = (String) claims.get("roles");
                    if(!roles.isEmpty() && roles.contains("admin")){
                        //将头信息继续向下传
                        context.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                }catch (Exception e){
                    context.setSendZuulResponse(false); //终止运行
                }
            }
        }
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(403);
        context.setResponseBody("权限不足");
        context.getResponse().setContentType("application/json;charset=utf-8");
        return null;
    }
}
