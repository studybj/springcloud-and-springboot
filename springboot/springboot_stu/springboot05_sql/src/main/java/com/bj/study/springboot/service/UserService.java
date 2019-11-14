package com.bj.study.springboot.service;

import com.bj.study.springboot.entity.JpaUser;
import com.bj.study.springboot.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 新增一个用户
     * @param user
     */
    void create(User user);
    /**
     * 根据name删除一个用户
     * @param name
     */
    void deleteByName(String name);
    /**
     * 获取所有用户信息
     */
    List<User> getAllUsers();
    List<JpaUser> getAllUsers2();
    List<User> getAllUsers3();
    List<User> getAllUsers4();
    String getAllUsersByRedis(String key);
    /**
     * 获取用户总量
     */
    Integer getAllUserCount();
    /**
     * 删除所有用户
     */
    void deleteAllUsers();
}
