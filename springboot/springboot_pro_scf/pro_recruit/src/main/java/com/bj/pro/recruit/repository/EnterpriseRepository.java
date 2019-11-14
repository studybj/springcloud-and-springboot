package com.bj.pro.recruit.repository;

import com.bj.pro.recruit.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

//JpaSpecificationExecutor 实现复杂条件查询
public interface EnterpriseRepository extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
    public List<Enterprise> findByIshot(String ishot);
}
