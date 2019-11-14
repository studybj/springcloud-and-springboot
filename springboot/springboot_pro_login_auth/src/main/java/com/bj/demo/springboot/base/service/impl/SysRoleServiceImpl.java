package com.bj.demo.springboot.base.service.impl;

import com.bj.demo.springboot.base.dao.SysRoleMapper;
import com.bj.demo.springboot.base.entity.SysRole;
import com.bj.demo.springboot.base.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;
    @Override
    public SysRole selectById(String roleId) {
        return roleMapper.get(roleId);
    }
}
