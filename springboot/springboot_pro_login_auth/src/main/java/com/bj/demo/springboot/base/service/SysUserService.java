package com.bj.demo.springboot.base.service;

import com.bj.demo.springboot.base.entity.SysUser;

public interface SysUserService {
   SysUser selectByName(String username);
}
