package com.bj.pro.user.repository;

import com.bj.pro.user.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//JpaSpecificationExecutor 实现复杂条件查询
public interface AdminRepository extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {
    Admin findByLoginname(String loginname);
    int countByLoginname(String loginname);
}
