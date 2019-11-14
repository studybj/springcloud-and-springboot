package com.bj.demo.system.service;

import com.bj.demo.system.entity.User;

import java.util.List;

public interface UserService {
    public void add(User user) throws Exception;
    public User getUserById(String id) throws Exception;
    public User getUserByUserName(String name) throws Exception;
    public List<User> getUserList() throws Exception;
}
