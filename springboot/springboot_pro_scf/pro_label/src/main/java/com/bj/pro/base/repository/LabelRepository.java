package com.bj.pro.base.repository;

import com.bj.pro.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//JpaSpecificationExecutor 实现复杂条件查询
public interface LabelRepository extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label > {
}
