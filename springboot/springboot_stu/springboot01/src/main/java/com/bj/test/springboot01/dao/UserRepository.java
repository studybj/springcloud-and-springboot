package com.bj.test.springboot01.dao;

import com.bj.test.springboot01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
}
