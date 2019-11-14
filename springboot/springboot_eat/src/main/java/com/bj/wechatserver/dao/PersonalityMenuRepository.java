package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.menu.PersonalityMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonalityMenuRepository extends JpaRepository<PersonalityMenu,Integer>, JpaSpecificationExecutor<PersonalityMenu> {
}
