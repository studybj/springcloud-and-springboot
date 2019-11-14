package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.TwMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TwMaterialRepository extends JpaRepository<TwMaterial,Integer>, JpaSpecificationExecutor<TwMaterial> {
}
