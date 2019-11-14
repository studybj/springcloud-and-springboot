package com.bj.pro.friend.controller;

import com.bj.pro.friend.client.UserClient;
import com.bj.pro.friend.entity.Friend;
import com.bj.pro.friend.service.FriendService;
import entity.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private HttpServletRequest hrequest;
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserClient userClient;
    /**
     * @param friendid
     * @param type 1为点击喜欢按钮，2为点击不喜欢按钮，其中喜欢按钮点击1次为喜欢，再点击为取消喜欢，相当于删除关系
     * @return
     */
    @PostMapping("/{friendid}/{type}")
    public Result add(@PathVariable String friendid, @PathVariable String type){
        //1.获取登录用户的id
        Claims claims = (Claims) hrequest.getAttribute("claims_user");
        if (claims == null){
            return ResultUtil.error("权限不足");
        }
        String userid = claims.getId();
        //先查询我和对方是否已有关系
        Friend friend = friendService.findById(userid, friendid);
        if (friend == null){//我和对方没关系，查询对方和我的关系
            if (!type.isEmpty()){
                if (type.equals("1")){//表示点击的是喜欢按钮
                    //查询对方和我的关系
                    Friend reversefriend = friendService.findById(friendid, userid);
                    if (reversefriend == null){//对方和我也没关系
                        friendService.save(userid, friendid, "1");
                    }else {
                        if (friend.getIslike().equals("0")) {//表示我对方不喜欢我
                            friendService.save(userid, friendid, "1");
                        }else if (friend.getIslike().equals("1")){//表示对方喜欢我
                            friendService.save(userid, friendid, "2");
                            friendService.update(friendid, userid, "2");
                        }else {
                            return ResultUtil.error("参数异常");
                        }
                    }
                    userClient.updateFanscountAndFollowcount(userid, friendid, 1);
                }else if (type.equals("2")){//表示点击的是不喜欢按钮
                    friendService.save(userid, friendid, "0");
                }else {
                    return ResultUtil.error("参数异常");
                }
            }else{
                return ResultUtil.error("参数异常");
            }
        }else {//我和对方有关系
            if (!type.isEmpty()){
                if (type.equals("1")){//表示点击的是喜欢按钮
                    if (friend.getIslike().equals("0")) {//表示我原来不喜欢对方，现在喜欢了
                        friendService.update(userid, friendid, "1");
                        userClient.updateFanscountAndFollowcount(userid, friendid, 1);
                    }else if (friend.getIslike().equals("1")){//表示我原来喜欢对方，现在取消喜欢了，即删除关系
                        friendService.delete(userid, friendid);
                        userClient.updateFanscountAndFollowcount(userid, friendid, -1);
                    }else if (friend.getIslike().equals("2")){//表示原来互相喜欢对方，现在我不喜欢对方了
                        friendService.delete(userid, friendid);
                        friendService.update(friendid, userid, "1");
                    }else {
                        return ResultUtil.error("参数异常");
                    }
                }else if (type.equals("2")){//表示点击的是不喜欢按钮
                    if (friend.getIslike().equals("0")) {//一般不会出现这种情况，表示我原来不喜欢对方，现在还是不喜欢了
                        //friendService.update(userid, friendid, "1");
                        return ResultUtil.error("已经不喜欢了");
                    }else if (friend.getIslike().equals("1")){//表示我原来喜欢对方，现在不喜欢了
                        friendService.update(userid, friendid, "0");
                        userClient.updateFanscountAndFollowcount(userid, friendid, -1);
                    }else if (friend.getIslike().equals("2")){//表示原来互相喜欢对方，现在我不喜欢对方了
                        friendService.update(userid, friendid, "0");
                        userClient.updateFanscountAndFollowcount(userid, friendid, -1);
                        friendService.update(friendid, userid, "1");
                    }else {
                        return ResultUtil.error("参数异常");
                    }
                }else {
                    return ResultUtil.error("参数异常");
                }
            }else{
                return ResultUtil.error("参数异常");
            }
        }
        return ResultUtil.success("更新成功");
    }
    //删除好友
    @PostMapping("/{friendid}")
    public Result add(@PathVariable String friendid) {
        //1.获取登录用户的id
        Claims claims = (Claims) hrequest.getAttribute("claims_user");
        if (claims == null) {
            return ResultUtil.error("权限不足");
        }
        String userid = claims.getId();

        friendService.delete(userid, friendid);
        userClient.updateFanscountAndFollowcount(userid, friendid, -1);
        return ResultUtil.success("删除成功");
    }

}
