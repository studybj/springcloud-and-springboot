package com.bj.demo.system.service.impl;

import com.bj.demo.system.dao.UserMapper;
import com.bj.demo.system.entity.User;
import com.bj.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void add(User user) throws Exception {
        userMapper.insert(user);
    }
    @Override
    public User getUserById(String id) throws Exception {
        return userMapper.seletById(id);
    }
    @Override
    public User getUserByUserName(String name) throws Exception {
        return userMapper.seletByName(name);
    }
    @Override
    public List<User> getUserList() throws Exception {
        return null;
    }
    public String login() throws Exception {
        return null;
    }
    public String register() throws Exception {
        return null;
    }
    public String logout() throws Exception {
        return null;
    }
}
