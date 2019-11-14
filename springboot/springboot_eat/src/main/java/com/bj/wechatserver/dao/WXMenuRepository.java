package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface WXMenuRepository extends JpaRepository<Menu,Integer>, JpaSpecificationExecutor<Menu> {
    List<Menu> findByNameAndStatus(String name,Integer status);
    void deleteMenusByPid(Integer pid);
    List<Menu> findByStatus(Integer status);
}
