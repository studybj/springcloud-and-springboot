package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.LsMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LsMaterialRepository extends JpaRepository<LsMaterial,String>, JpaSpecificationExecutor<LsMaterial> {
}
