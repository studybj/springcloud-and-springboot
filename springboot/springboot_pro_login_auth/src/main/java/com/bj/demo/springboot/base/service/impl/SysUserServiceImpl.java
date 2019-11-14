package com.bj.demo.springboot.base.service.impl;

import com.bj.demo.springboot.base.dao.SysUserMapper;
import com.bj.demo.springboot.base.entity.SysUser;
import com.bj.demo.springboot.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired(required = false)
    private SysUserMapper userMapper;

    public SysUser selectById(String id) {
        return userMapper.get(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.findByUsername(name);
    }
}
