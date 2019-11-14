package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.UserTagX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserTagXRepository extends JpaRepository<UserTagX,String> , JpaSpecificationExecutor<UserTagX> {
    List<UserTagX> findByUserid(String userid);
    List<UserTagX> findByTagid(String tagid);
    List<UserTagX> findByUseridAndTagid(String userid, String tagid);
}
