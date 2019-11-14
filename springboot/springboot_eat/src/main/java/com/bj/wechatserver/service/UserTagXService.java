package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.UserTagX;

import java.util.List;

public interface UserTagXService {
    void save(UserTagX userTagX);
    void save(List<UserTagX> lists);
    List<UserTagX> findByParam(UserTagX tagX,int type);
    List<String> findByParam(String tagidlist);
}
