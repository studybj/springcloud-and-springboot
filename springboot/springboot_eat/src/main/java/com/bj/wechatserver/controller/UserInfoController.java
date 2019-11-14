package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.UserInfo;
import com.bj.wechatserver.service.UserInfoService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInfo")
@Slf4j
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(userInfoService.findAll(pageRequest).getContent(),userInfoService.count(null,null));
    }

    @RequestMapping("/listByParam")
    public ResultVo list(UserInfo userInfo, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(userInfoService.findByParam(userInfo, time, pageRequest).getContent(), userInfoService.count(userInfo,time));
    }

    @RequestMapping("/save")
    public ResultVo save(UserInfo userInfo){
        userInfoService.save(userInfo);
        return ResultVoUtil.success();
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam String[] keyReplyId){
        userInfoService.delete(keyReplyId);
        return ResultVoUtil.success();
    }
    /**
     * @param userInfo
     * @param updateType 更新类型  1 更新备注名，2更新订阅状态  3 更新是否黑名单,4更新分组信息(失效),5更新标签信息
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(UserInfo userInfo,int updateType){
        Map result = userInfoService.update(userInfo,updateType);
        return  ResultVoUtil.result(result);
    }
    @RequestMapping("/batch_update")
    public @ResponseBody ResultVo batchUpdate(@RequestParam String userlist,int updateType) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, UserInfo.class);
        List<UserInfo> lists =  (List<UserInfo>)mapper.readValue(userlist, jt);

        Map result = userInfoService.batchUpdate(lists,updateType);
        return  ResultVoUtil.result(result);
    }
}
