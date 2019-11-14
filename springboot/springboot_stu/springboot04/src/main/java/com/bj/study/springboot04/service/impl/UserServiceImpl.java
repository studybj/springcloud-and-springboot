package com.bj.study.springboot04.service.impl;

import com.bj.study.springboot04.bean.UserInfo2;
import com.bj.study.springboot04.dao.UserRepository;
import com.bj.study.springboot04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserInfo2> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public UserInfo2 findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(UserInfo2 user) {
        userRepository.save(user);
    }

    @Override
    public void edit(UserInfo2 user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
