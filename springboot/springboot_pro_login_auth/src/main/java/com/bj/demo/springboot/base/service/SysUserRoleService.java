package com.bj.demo.springboot.base.service;

import com.bj.demo.springboot.base.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService {
    List<SysUserRole> listByUserId(String userId);
}
