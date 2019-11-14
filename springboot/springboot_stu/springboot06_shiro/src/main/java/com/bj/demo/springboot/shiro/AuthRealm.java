package com.bj.demo.springboot.shiro;

import com.bj.demo.springboot.dao.SysUserMapper;
import com.bj.demo.springboot.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private SysUserMapper userMapper;
    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权中。。。。");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        //根据用户id获取用户角色，然后根据角色获取权限

        return info;
    }
    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证中。。。。");
        //获取用户名，查询数据库，比对密码，通过后去授权
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        SysUser user = userMapper.findByUsername(utoken.getUsername());
        if(user == null){
            throw new UnknownAccountException("用户名不存在");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
