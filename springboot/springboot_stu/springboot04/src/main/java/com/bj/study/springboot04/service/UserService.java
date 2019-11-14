package com.bj.study.springboot04.service;

import com.bj.study.springboot04.bean.UserInfo2;

import java.util.List;

public interface UserService {
    public List<UserInfo2> getUserList();

    public UserInfo2 findUserById(long id);

    public void save(UserInfo2 user);

    public void edit(UserInfo2 user);

    public void delete(long id);
}
