package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.menu.PersonalityMenu;

import java.util.List;

public interface PersonalityMenuService {

    void save(PersonalityMenu menu) throws Exception;
    void delete(Integer[] ids);
    List<PersonalityMenu> findAll();
}
