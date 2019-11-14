package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AccountService {
    void save(Account account);
    void save(List<Account> lists);

    void delete(String[] ids);

    Map update(String[] ids, int updateType);

    Account findById(String id);
    List<Account> findAll();

    List<Account> findByParam(Account account);

    Page<Account> findAll(Pageable pageable);
    Page<Account> findByParam(Account account, String time, Pageable pageable);

    long count(Account account, String time);
}
