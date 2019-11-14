package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.Kefu;
import com.bj.wechatserver.entity.UserTag;
import com.bj.wechatserver.service.KefuService;
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
@RequestMapping("/kefu")
@Slf4j
public class KefuController {
    @Autowired
    private KefuService kefuService;

    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(kefuService.findAll(pageRequest).getContent(),kefuService.count(null,null));
    }

    @RequestMapping("/listByParam")
    public ResultVo list(Kefu kefu, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(kefuService.findByParam(kefu, time, pageRequest).getContent(), kefuService.count(kefu,time));
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam String[] ids){
        kefuService.delete(ids);
        return ResultVoUtil.success();
    }
    /**
     * @param updateType 更新类型  1 更改状态,2
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(String[] ids, int updateType){
        System.out.println("ids = [" + ids.toString() + "], updateType = [" + updateType + "]");
        Map result = kefuService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }




    @RequestMapping("/save")
    public ResultVo save(Kefu kefu){
        kefuService.save(kefu);
        return ResultVoUtil.success();
    }
    @RequestMapping("/batch_save")
    public ResultVo batchSave(@RequestParam String list, String id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, UserTag.class);
        List<Kefu> lists =  (List<Kefu>)mapper.readValue(list, jt);

        if(id != null && !"".equals(id)){
            Kefu info = lists.get(0);
            info.setId(id);
            kefuService.save(info);
        }else{
            kefuService.save(lists);
        }
        return ResultVoUtil.success();
    }


}
