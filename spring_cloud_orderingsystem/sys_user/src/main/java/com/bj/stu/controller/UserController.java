package com.bj.stu.controller;

import com.bj.stu.entity.Result;
import com.bj.stu.entity.User;
import com.bj.stu.service.UserService;
import com.bj.stu.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result findAll(){
        return ResultUtil.success("查询成功", userService.findAll());
    }
    @GetMapping("/{userId}")
    public Result findById(@PathVariable("userId") long id){
        return ResultUtil.success("查询成功", userService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody User user){
        userService.save(user);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        userService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable long userId){
        userService.delete(userId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{userId}")
    public Result update(@PathVariable long userId, @RequestBody User user){
        user.setId(userId);
        userService.update(user);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody User user){
        return ResultUtil.success("查询成功", userService.search(user));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody User user, @PathVariable int page, @PathVariable int size){
//        PageRequest pageRequest = PageRequest.of(page - 1,size);
//        Page<User> pageinfo = userService.searchPage(user, pageRequest);
//        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
        return null;
    }

}
