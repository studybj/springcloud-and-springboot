package com.bj.demo.springboot.base.service.impl;

import com.bj.demo.springboot.base.dao.SysUserRoleMapper;
import com.bj.demo.springboot.base.entity.SysUser;
import com.bj.demo.springboot.base.entity.SysUserRole;
import com.bj.demo.springboot.base.service.SysUserRoleService;
import com.bj.demo.springboot.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired(required = false)
    private SysUserRoleMapper userRoleMapper;
    @Override
    public List<SysUserRole> listByUserId(String userId) {
        return userRoleMapper.listByUserId(userId);
    }
}
