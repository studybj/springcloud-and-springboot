package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.TwMaterialBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TwBaseMaterialRepository extends JpaRepository<TwMaterialBase,String>, JpaSpecificationExecutor<TwMaterialBase> {
}
