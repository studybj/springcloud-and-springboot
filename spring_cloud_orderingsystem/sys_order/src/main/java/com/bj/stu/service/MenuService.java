package com.bj.stu.service;


import com.bj.stu.entity.Menu;
import com.bj.stu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Menu> findAll(){
        return menuRepository.findAll();
    }
    public Menu findById(long id){
        Menu menu = (Menu) redisTemplate.opsForValue().get("menu_" + id);
        if (menu == null){
            menu = menuRepository.findById(id);
            redisTemplate.opsForValue().set("menu_" + id, menu);
        }
        return menu;
    }
    public int count(){
        return menuRepository.count();
    }
    public void save(Menu menu){
        menuRepository.save(menu);
    }
    public void update(Menu menu){
        redisTemplate.delete("menu_" + menu.getId());
        menuRepository.update(menu);
    }
    public void deleteAll(){
        redisTemplate.delete("menu_*");
        menuRepository.deleteAll();
    }
    public void delete(long id){
        redisTemplate.delete("menu_" + id);
        menuRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Menu> search(Menu menu){
        return null;
    }

    public Page<Menu> searchPage(Menu menu, Pageable pageable) {
        return null;
    }
}
