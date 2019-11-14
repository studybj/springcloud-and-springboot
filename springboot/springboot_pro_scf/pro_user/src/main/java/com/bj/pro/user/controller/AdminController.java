package com.bj.pro.user.controller;

import com.bj.pro.user.entity.Admin;
import com.bj.pro.user.service.AdminService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtil;
import utils.ResultUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", adminService.findAll());
    }
    @GetMapping("/{adminId}")
    public Result findById(@PathVariable("adminId") String id){
        return ResultUtil.success("查询成功", adminService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        adminService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{adminId}")
    public Result delete(@PathVariable String adminId){
        adminService.delete(adminId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{adminId}")
    public Result update(@PathVariable String adminId, @RequestBody Admin admin){
        admin.setId(adminId);
        adminService.update(admin);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Admin admin){
        return ResultUtil.success("查询成功", adminService.search(admin));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Admin admin, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Admin> pageinfo = adminService.searchPage(admin, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Admin login = adminService.login(admin);
        //做一些操作使得前后端可通话，采用jwt来实现
        //创建令牌
        String token = jwtUtil.createJwt(admin.getId(), admin.getLoginname(), "admin");
        Map map = new HashMap();
        map.put("token", token);
        map.put("roles", "admin");
        map.put("pormission","");
        return ResultUtil.success("登录成功", map);
    }
}
