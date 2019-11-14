package com.bj.shiro.demo.service;

import com.bj.shiro.demo.entity.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
