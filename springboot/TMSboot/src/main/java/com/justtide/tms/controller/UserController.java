package com.justtide.tms.controller;

import com.justtide.tms.common.Result;
import com.justtide.tms.entity.User;
import com.justtide.tms.service.UserService;
import com.justtide.tms.util.JwtUtil;
import com.justtide.tms.util.ResultUtil;
import com.justtide.tms.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", userService.findAll());
    }
    @GetMapping("/{userId}")
    public Result findById(@PathVariable("userId") String id){
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
    public Result delete(@PathVariable String userId){
        userService.delete(userId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{userId}")
    public Result update(@PathVariable String userId, @RequestBody User user){
        user.setUserNo(userId);
        userService.update(user);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody User user){
        return ResultUtil.success("查询成功", userService.search(user));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody User user, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<User> pageinfo = userService.searchPage(user, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
    /**
     * 登录
     */
    @PostMapping("/login/{code}")
    public Result login(@PathVariable String code, @RequestBody UserVo user) {
        User userRes = userService.login(user);
        if(userRes == null){
            return ResultUtil.error("账号或密码错误，请重试！");
        }
        //存储登录信息,原来是将登录信息及权限等信息存入session中
        // 此处采用jwt来实现前后端通信
        String token =jwtUtil.createJwt(userRes.getUserNo(), userRes.getUserName(), "user");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return ResultUtil.success("登录成功", map);
    }
    /**
     * 登录后获取用户信息，包括角色，菜单等
     */
    @GetMapping("/userinfo")
    public Result getLoginUserInfo() {
        Map<String, Object> map = new HashMap<>();
        String []a = {"t","t01","t011","t012","t02","t021","t022","t03","t031","t04","t041","t05","t051","t052","t06","t061","t062","t07","t071","t0711","t0712","t072","t0721","t0722","t073","t0731","t08","t081","t0811","t0812"};
        int []b = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,41,42};
        map.put("last_login_time", "");
        map.put("name", "user");
        map.put("roles", "roles");
        map.put("menus", a);
        map.put("menusss", b);
        return ResultUtil.success("获取成功", map);
    }

}
