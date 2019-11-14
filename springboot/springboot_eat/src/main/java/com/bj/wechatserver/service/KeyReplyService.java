package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.KeyReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeyReplyService {

    List<KeyReply> findAll();
    List<KeyReply> findByParam(KeyReply keyReply);

    Page<KeyReply> findAll(Pageable pageable);
    Page<KeyReply> findByParam(KeyReply keyReply,Pageable pageable);

    KeyReply findByKey(String key, Integer status);

    List<KeyReply> findByIsFirstAndStatus(Integer first, Integer status);
    KeyReply findByKey(String key, Integer first, Integer status);

    void save(KeyReply keyReply);

    void delete(String[] ids);

    long count(KeyReply keyReply);
}
