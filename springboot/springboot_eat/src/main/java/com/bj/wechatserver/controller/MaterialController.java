package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.LsMaterial;
import com.bj.wechatserver.entity.Material;
import com.bj.wechatserver.entity.UserTag;
import com.bj.wechatserver.service.LsMaterialService;
import com.bj.wechatserver.service.MaterialService;
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
@RequestMapping("/material")
@Slf4j
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private LsMaterialService lsmaterialService;
    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(materialService.findAll(pageRequest).getContent(),materialService.count(null,null));
    }

    @RequestMapping("/listByParam")
    public ResultVo list(Material material, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(materialService.findByParam(material, time, pageRequest).getContent(), materialService.count(material,time));
    }

    @RequestMapping("/save")
    public ResultVo save(Material material){
        materialService.save(material);
        return ResultVoUtil.success();
    }
    @RequestMapping("/batch_save")
    public ResultVo batchSave(@RequestParam String list, String id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, Material.class);
        List<Material> lists =  (List<Material>)mapper.readValue(list, jt);

        if(id != null && !"".equals(id)){
            Material info = lists.get(0);
            info.setId(id);
            materialService.save(info);
        }else{
            materialService.save(lists);
        }
        return ResultVoUtil.success();
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam String[] id){
        materialService.delete(id);
        return ResultVoUtil.success();
    }
    /**
     * @param updateType 更新类型  1 更改状态,2 更新粉丝数(新增) 3 更新粉丝数 减少
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(String[] ids, int updateType){
        System.out.println("ids = [" + ids + "], updateType = [" + updateType + "]");
        Map result = materialService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }
    @RequestMapping("/existInfo")
    public @ResponseBody ResultVo existInfo(@RequestParam String list, Integer id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, Material.class);
        List<Material> lists =  (List<Material>)mapper.readValue(list, jt);

        Material material = new Material();
        for(Material taginfo : lists){

        }
        return ResultVoUtil.success();
    }
    @RequestMapping("/findTag")
    public @ResponseBody ResultVo findTag(){
        LsMaterial material = new LsMaterial();
        material.setStatus(1);
        return  ResultVoUtil.success(lsmaterialService.findByParam(material));
    }

    @RequestMapping("/list_ls")
    public ResultVo listls(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(lsmaterialService.findAll(pageRequest).getContent(),lsmaterialService.count(null,null));
    }

    @RequestMapping("/listByParam_ls")
    public ResultVo listls(LsMaterial material, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(lsmaterialService.findByParam(material, time, pageRequest).getContent(), lsmaterialService.count(material,time));
    }

    @RequestMapping("/save_ls")
    public ResultVo savels(LsMaterial material){
        lsmaterialService.save(material);
        return ResultVoUtil.success();
    }
    @RequestMapping("/batch_save_ls")
    public ResultVo batchSavels(@RequestParam String list, String id) throws IOException {
        //jackson对象
        ObjectMapper mapper = new ObjectMapper();
        //使用jackson将json转为List<User>
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, LsMaterial.class);
        List<LsMaterial> lists =  (List<LsMaterial>)mapper.readValue(list, jt);

        if(id != null && !"".equals(id)){
            LsMaterial info = lists.get(0);
            info.setId(id);
            lsmaterialService.save(info);
        }else{
            lsmaterialService.save(lists);
        }
        return ResultVoUtil.success();
    }

    @RequestMapping("/delete_ls")
    public ResultVo deletels(@RequestParam String[] id){
        lsmaterialService.delete(id);
        return ResultVoUtil.success();
    }
    /**
     * @param updateType 更新类型  1 更改状态,2 更新粉丝数(新增) 3 更新粉丝数 减少
     * @return
     */
    @RequestMapping("/update_ls")
    public @ResponseBody ResultVo updatels(String[] ids, int updateType){
        System.out.println("ids = [" + ids + "], updateType = [" + updateType + "]");
        Map result = lsmaterialService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }
}
