package com.bj.stu.repository;

import com.bj.stu.entity.Admin;

import java.util.List;

public interface AdminRepository {
    public List<Admin> findAll();

    public Admin findById(long id);

    public int count();

    public void save(Admin admin);

    public void update(Admin admin);

    public void deleteById(long id);

    public void deleteAll();
}
