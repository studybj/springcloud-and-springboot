package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialRepository extends JpaRepository<Material,String>, JpaSpecificationExecutor<Material> {
}
