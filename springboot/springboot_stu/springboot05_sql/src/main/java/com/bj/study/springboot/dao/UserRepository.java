package com.bj.study.springboot.dao;

import com.bj.study.springboot.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<JpaUser, String> {
}
