package com.bj.demo.springboot.base.dto;

import com.bj.demo.springboot.base.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
//构建真正用于SpringSecurity登录的安全用户(UserDetails)
public class SecurityUser extends SysUser implements UserDetails {
    public SecurityUser(SysUser user){
        if(user != null){
            BeanUtils.copyProperties(user,this);
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            authorities.add(authority);
        }
        return authorities;
    }
    //账户是否未过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //指定用户是否解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否可用 ,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }
}
