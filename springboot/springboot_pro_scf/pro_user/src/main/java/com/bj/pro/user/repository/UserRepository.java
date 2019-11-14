package com.bj.pro.user.repository;

import com.bj.pro.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//JpaSpecificationExecutor 实现复杂条件查询
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByMobile(String mobile);
}
