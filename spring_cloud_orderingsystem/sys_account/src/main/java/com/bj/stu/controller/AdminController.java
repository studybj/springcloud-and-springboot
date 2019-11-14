package com.bj.stu.controller;

import com.bj.stu.entity.Admin;
import com.bj.stu.entity.Result;
import com.bj.stu.service.AdminService;
import com.bj.stu.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public Result findAll(){
        return ResultUtil.success("查询成功", adminService.findAll());
    }
    @GetMapping("/{adminId}")
    public Result findById(@PathVariable("adminId") long id){
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
    public Result delete(@PathVariable long adminId){
        adminService.delete(adminId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{adminId}")
    public Result update(@PathVariable long adminId, @RequestBody Admin admin){
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
//        PageRequest pageRequest = PageRequest.of(page - 1,size);
//        Page<Admin> pageinfo = adminService.searchPage(admin, pageRequest);
//        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
        return null;
    }

}
