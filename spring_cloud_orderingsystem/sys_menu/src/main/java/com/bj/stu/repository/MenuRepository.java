package com.bj.stu.repository;

import com.bj.stu.entity.Menu;

import java.util.List;

public interface MenuRepository {
    public List<Menu> findAll();

    public Menu findById(long id);

    public int count();

    public void save(Menu menu);

    public void update(Menu menu);

    public void deleteById(long id);

    public void deleteAll();
}
