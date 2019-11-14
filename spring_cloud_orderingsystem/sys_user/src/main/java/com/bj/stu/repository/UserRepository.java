package com.bj.stu.repository;

import com.bj.stu.entity.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAll();

    public User findById(long id);

    public int count();

    public void save(User user);

    public void update(User user);

    public void deleteById(long id);

    public void deleteAll();
}
