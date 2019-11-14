package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserTagRepository extends JpaRepository<UserTag,Integer>, JpaSpecificationExecutor<UserTag> {
    List<UserTag> findByName(String tagName);
}
