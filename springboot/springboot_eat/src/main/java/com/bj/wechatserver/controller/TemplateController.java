package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.Template;
import com.bj.wechatserver.entity.Template;
import com.bj.wechatserver.entity.UserTag;
import com.bj.wechatserver.service.TemplateService;
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
@RequestMapping("/template")
@Slf4j
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(templateService.findAll(pageRequest).getContent(), templateService.count(null,null));
    }

    @RequestMapping("/listByParam")
    public ResultVo list(Template template, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(templateService.findByParam(template, time, pageRequest).getContent(), templateService.count(template,time));
    }

    @RequestMapping("/save")
    public ResultVo save(Template template){
        templateService.save(template);
        return ResultVoUtil.success();
    }
    @RequestMapping("/batch_save")
    public ResultVo batchSave(@RequestParam String list, String id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, UserTag.class);
        List<Template> lists =  (List<Template>)mapper.readValue(list, jt);

        if(id != null && !"".equals(id)){
            Template info = lists.get(0);
            info.setId(id);
            templateService.save(info);
        }else{
            templateService.save(lists);
        }
        return ResultVoUtil.success();
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam String[] ids){
        templateService.delete(ids);
        return ResultVoUtil.success();
    }
    /**
     * @param updateType 更新类型  1 更改状态,2
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(String[] ids, int updateType){
        Map result = templateService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }
}
