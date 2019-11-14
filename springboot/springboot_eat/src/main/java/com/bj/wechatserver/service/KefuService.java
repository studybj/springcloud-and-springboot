package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.Kefu;
import com.bj.wechatserver.entity.UserTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface KefuService {
    void save(Kefu kefu);
    void save(List<Kefu> lists);

    void delete(String[] ids);

    Map update (String[] ids, int updateType);

    Kefu findById(String id);
    List<Kefu> findAll();

    List<Kefu> findByNickName(String name);
    List<Kefu> findByParam(Kefu kefu);

    Page<Kefu> findAll(Pageable pageable);
    Page<Kefu> findByParam(Kefu kefu, String time, Pageable pageable);

    long count(Kefu kefu, String time);
}
