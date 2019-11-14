package com.bj.test.demo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {
        @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息

        // 判断用户是否存在
        // 返回UserDetails实现类
        return new User("zhangsan", new BCryptPasswordEncoder(4).encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
