package com.bj.pro.recruit.controller;

import com.bj.pro.recruit.entity.Enterprise;
import com.bj.pro.recruit.service.EnterpriseService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", enterpriseService.findAll());
    }
    @GetMapping("/{enterpriseId}")
    public Result findById(@PathVariable("enterpriseId") String id){
        return ResultUtil.success("查询成功", enterpriseService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Enterprise enterprise){
        enterpriseService.save(enterprise);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        enterpriseService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{enterpriseId}")
    public Result delete(@PathVariable String enterpriseId){
        enterpriseService.delete(enterpriseId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{enterpriseId}")
    public Result update(@PathVariable String enterpriseId, @RequestBody Enterprise enterprise){
        enterprise.setId(enterpriseId);
        enterpriseService.update(enterprise);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Enterprise enterprise){
        return ResultUtil.success("查询成功", enterpriseService.search(enterprise));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Enterprise enterprise, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Enterprise> pageinfo = enterpriseService.searchPage(enterprise, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    @GetMapping("/search/hotlist")
    public Result hotlist(){
        return ResultUtil.success("查询成功", enterpriseService.hotlist("1"));
    }
    /**
     *
     */
}
