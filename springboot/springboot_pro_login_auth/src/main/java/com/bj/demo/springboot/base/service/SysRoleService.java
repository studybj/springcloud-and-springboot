package com.bj.demo.springboot.base.service;

import com.bj.demo.springboot.base.entity.SysRole;

public interface SysRoleService {
    SysRole selectById(String roleId);
}
