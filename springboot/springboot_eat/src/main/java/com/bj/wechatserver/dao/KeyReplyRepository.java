package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.KeyReply;
import com.bj.wechatserver.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface KeyReplyRepository extends JpaRepository<KeyReply,String>, JpaSpecificationExecutor<KeyReply> {
    KeyReply findByRkeyAndStatus(String key, Integer status);
    List<KeyReply> findByIsfirstAndStatus(Integer first, Integer status);
}
