package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.Kefu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface KefuRepository extends JpaRepository<Kefu,String> , JpaSpecificationExecutor<Kefu> {
    List<Kefu> findByNickname(String name);
}
