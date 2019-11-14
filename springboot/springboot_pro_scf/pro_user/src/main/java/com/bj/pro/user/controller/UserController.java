package com.bj.pro.user.controller;

import com.bj.pro.user.entity.User;
import com.bj.pro.user.service.UserService;
import com.bj.pro.user.vo.UserLoginVo;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll() {
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", userService.findAll());
    }

    @GetMapping("/{userId}")
    public Result findById(@PathVariable("userId") String id) {
        return ResultUtil.success("查询成功", userService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody User user) {
        userService.save(user);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll() {
        userService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable String userId) {
        userService.delete(userId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{userId}")
    public Result update(@PathVariable String userId, @RequestBody User user) {
        user.setId(userId);
        userService.update(user);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody User user) {
        return ResultUtil.success("查询成功", userService.search(user));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody User user, @PathVariable int page, @PathVariable int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<User> pageinfo = userService.searchPage(user, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    /**
     * 发送短信验证码
     */
    @PostMapping("/sendSms/{mobile}")
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return ResultUtil.success("发送成功");
    }

    /**
     * 注册
     */
    @PostMapping("/register/{code}")
    public Result register(@PathVariable String code, @RequestBody User user) {
        String validate_code = (String) redisTemplate.opsForValue().get("validate_code_" + user.getMobile());
        if (validate_code.isEmpty()) {
            return ResultUtil.error("验证码不存在或已失效");
        }
        if (!validate_code.equals(code)){
            return ResultUtil.error("验证码错误");
        }
        userService.save(user);
        return ResultUtil.success("注册成功");
    }
    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginVo userVo) {
        //多种登录方式，vo中就需添加其字段，且在库中唯一
        userService.login(userVo);
        return ResultUtil.success("登录成功");
    }
}
