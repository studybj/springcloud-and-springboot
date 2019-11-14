package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.menu.Menu;
import java.util.List;
import java.util.Map;

public interface WXMenuService {

    void save(Menu menu) throws Exception;
    void save(List<Menu> lists);
    Map update(Integer[] ids, int updateType) throws Exception;
    Menu findById(Integer id);

    List<Menu> findAll();
    List<Menu> findByParam(Menu menu, int change);
    List<Menu> findByMenuNameAndStatus(String menuname,Integer status);

    void delete(Integer[] ids);

    long count(Menu menu);
}
