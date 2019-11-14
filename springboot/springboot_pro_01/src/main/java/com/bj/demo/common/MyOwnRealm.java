package com.bj.demo.common;

import com.bj.demo.system.entity.User;
import com.bj.demo.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyOwnRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException,Exception {
        String username = (String) token.getPrincipal();
        User user = userService.getUserByUserName(username);
        if(user==null){
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
        //判断用户是否被锁定
        if("".equals(user.getLocked())){

        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt())   ,
                getName());                   //realm name

        return authenticationInfo;
    }
}
