package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.UserTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserTagService {

    void save(UserTag userTag);
    void save(List<UserTag> lists);
    Map update (Integer[] ids, int updateType);
    UserTag findById(Integer id);

    List<UserTag> findAll();
    List<UserTag> findByParam(UserTag userTag);
    List<UserTag> findByTagName(String tagname);

    Page<UserTag> findAll(Pageable pageable);
    Page<UserTag> findByParam(UserTag userTag, String time, Pageable pageable);

    void delete(Integer[] ids);

    long count(UserTag userTag, String time);
}
