package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.Material;
import com.bj.wechatserver.entity.TwMaterial;
import com.bj.wechatserver.entity.TwMaterialBase;
import com.bj.wechatserver.service.MaterialService;
import com.bj.wechatserver.service.TwMaterialService;
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
@RequestMapping("/material/tw")
@Slf4j
public class TwMaterialController {
    @Autowired
    private TwMaterialService twMaterialService;
    @Autowired
    private MaterialService materialService;
    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(twMaterialService.findAll(pageRequest).getContent(), twMaterialService.count(null,null));
    }

    @RequestMapping("/listByParam")
    public ResultVo list(TwMaterial material, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(twMaterialService.findByParam(material, time, pageRequest).getContent(), twMaterialService.count(material,time));
    }
    @RequestMapping("/listByParam_base")
    public ResultVo list_base(TwMaterialBase material, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(twMaterialService.findByParam_base(material, time, pageRequest).getContent(), twMaterialService.count_base(material,time));
    }

    @RequestMapping("/save")
    public ResultVo save(TwMaterial material){
        twMaterialService.save(material);
        return ResultVoUtil.success();
    }
    @RequestMapping("/save_base")
    public ResultVo save(TwMaterialBase material){
        twMaterialService.save_base(material);
        return ResultVoUtil.success();
    }
    @RequestMapping("/batch_save")
    public ResultVo batchSave(@RequestParam String list, Integer id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, TwMaterial.class);
        List<TwMaterial> lists =  (List<TwMaterial>)mapper.readValue(list, jt);

        if(id != null && !"".equals(id)){
            TwMaterial info = lists.get(0);
            info.setId(id);
            twMaterialService.save(info);
        }else{
            twMaterialService.save(lists);
        }
        return ResultVoUtil.success();
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam String[] id,int type){
        if(type == 1){
            twMaterialService.delete(id);
        }else{
            twMaterialService.delete_base(id);
        }
        return ResultVoUtil.success();
    }

    /**
     * @param updateType 更新类型  1 更改状态,2 更新粉丝数(新增) 3 更新粉丝数 减少
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(Integer[] ids, int updateType){
        System.out.println("ids = [" + ids + "], updateType = [" + updateType + "]");
        Map result = twMaterialService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }
    /**
     * @param updateType 更新类型  1 更改状态,2 更新显示封面 3 更新打开评论，4只可粉丝评论
     * @return
     */
    @RequestMapping("/update_base")
    public @ResponseBody ResultVo update(String ids, int updateType){
        Map result = twMaterialService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }
    @RequestMapping("/material")
    public @ResponseBody ResultVo material(String type){
        Material material = new Material();
        material.setStatus(1);
        material.setType(type);
        List<Material> result = materialService.findByParam(material);
        return  ResultVoUtil.success(result);
    }
    @RequestMapping("/twmaterial")
    public @ResponseBody ResultVo twmaterial(){
        List<TwMaterialBase> result = twMaterialService.findAll_base();
        return  ResultVoUtil.success(result);
    }
}
