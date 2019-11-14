package com.justtide.tms.controller;

import com.justtide.tms.common.Result;
import com.justtide.tms.entity.Role;
import com.justtide.tms.service.RoleService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", roleService.findAll());
    }
    @GetMapping("/{roleId}")
    public Result findById(@PathVariable("roleId") Integer id){
        return ResultUtil.success("查询成功", roleService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Role role){
        roleService.save(role);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        roleService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{roleId}")
    public Result delete(@PathVariable Integer roleId){
        roleService.delete(roleId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{roleId}")
    public Result update(@PathVariable Integer roleId, @RequestBody Role role){
        role.setRoleId(roleId);
        roleService.update(role);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Role role){
        return ResultUtil.success("查询成功", roleService.search(role));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Role role, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Role> pageinfo = roleService.searchPage(role, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
