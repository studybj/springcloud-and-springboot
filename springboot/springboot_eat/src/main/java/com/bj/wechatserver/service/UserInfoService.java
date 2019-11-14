package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
    void save(UserInfo userInfo);
    Map update (UserInfo userInfo, int updateType);
    Map batchUpdate (List<UserInfo> userlist, int updateType);
    UserInfo findByOpenId(String openid);

    List<UserInfo> findAll();
    List<UserInfo> findByParam(UserInfo userInfo);

    Page<UserInfo> findAll(Pageable pageable);
    Page<UserInfo> findByParam(UserInfo userInfo, String time, Pageable pageable);

    void delete(String[] ids);

    long count(UserInfo userInfo, String time);
}
