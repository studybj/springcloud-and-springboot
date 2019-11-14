package com.bj.study.springboot.service;

import com.bj.study.springboot.entity.JpaUser;
import com.bj.study.springboot.entity.User;

import java.util.List;

public interface UserService2 {
    User save(User user);
    User update(User user);
    User getUserById(String id);
    void delete(String id);
}
