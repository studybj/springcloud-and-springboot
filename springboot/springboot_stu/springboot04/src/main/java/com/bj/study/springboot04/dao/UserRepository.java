package com.bj.study.springboot04.dao;

import com.bj.study.springboot04.bean.UserInfo2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo2, Long> {
    UserInfo2 findById(long id);
    void deleteById(long id);
}
