package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.UserTag;
import com.bj.wechatserver.service.UserTagService;
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
@RequestMapping("/userTag")
@Slf4j
public class UserTagController {
    @Autowired
    private UserTagService userTagService;

    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(userTagService.findAll(pageRequest).getContent(),userTagService.count(null,null));
    }

    @RequestMapping("/listByParam")
    public ResultVo list(UserTag userTag, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(userTagService.findByParam(userTag, time, pageRequest).getContent(), userTagService.count(userTag,time));
    }

    @RequestMapping("/save")
    public ResultVo save(UserTag userTag){
        userTagService.save(userTag);
        return ResultVoUtil.success();
    }
    @RequestMapping("/batch_save")
    public ResultVo batchSave(@RequestParam String list, Integer id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, UserTag.class);
        List<UserTag> lists =  (List<UserTag>)mapper.readValue(list, jt);

        if(id != null && !"".equals(id)){
            UserTag info = lists.get(0);
            info.setId(id);
            userTagService.save(info);
        }else{
            userTagService.save(lists);
        }
        return ResultVoUtil.success();
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam Integer[] id){
        userTagService.delete(id);
        return ResultVoUtil.success();
    }
    /**
     * @param updateType 更新类型  1 更改状态,2 更新粉丝数(新增) 3 更新粉丝数 减少
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(Integer[] ids, int updateType){
        System.out.println("ids = [" + ids + "], updateType = [" + updateType + "]");
        Map result = userTagService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }
    @RequestMapping("/existInfo")
    public @ResponseBody ResultVo existInfo(@RequestParam String list, Integer id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, UserTag.class);
        List<UserTag> lists =  (List<UserTag>)mapper.readValue(list, jt);

        UserTag testTag = new UserTag();
        for(UserTag taginfo : lists){
            if(taginfo.getName() != null && !"".equals(taginfo.getName().trim())){
                testTag.setName(taginfo.getName());
                List<UserTag> resultList = userTagService.findByTagName(taginfo.getName().trim());
                if(resultList.size() > 0) {
                    if (id != null && !"".equals(id)) {
                        if (resultList.get(0).getId() !=id){
                            return ResultVoUtil.error(-1, "系统中已存在" + taginfo.getName() + "标签");
                        }
                    } else {
                        return ResultVoUtil.error(-1, "系统中已存在" + taginfo.getName() + "标签");
                    }
                }
            }else {
                return ResultVoUtil.error(-1,"标签名不能为空");
            }
        }
        return ResultVoUtil.success();
    }
    @RequestMapping("/findTag")
    public @ResponseBody ResultVo findTag(){
        UserTag userTag = new UserTag();
        userTag.setStatus(1);
        return  ResultVoUtil.success(userTagService.findByParam(userTag));
    }


}
