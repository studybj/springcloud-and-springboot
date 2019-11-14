package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.KeyReply;
import com.bj.wechatserver.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoRepository extends JpaRepository<UserInfo,String>, JpaSpecificationExecutor<UserInfo> {
    UserInfo findByOpenid(String openid);
}
