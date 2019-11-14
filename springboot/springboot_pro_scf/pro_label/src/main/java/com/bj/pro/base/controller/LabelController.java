package com.bj.pro.base.controller;

import com.bj.pro.base.entity.Label;
import com.bj.pro.base.service.LabelService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", labelService.findAll());
    }
    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String id){
        return ResultUtil.success("查询成功", labelService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        labelService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{labelId}")
    public Result delete(@PathVariable String labelId){
        labelService.delete(labelId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{labelId}")
    public Result update(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Label label){
        return ResultUtil.success("查询成功", labelService.search(label));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Label label, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Label> pageinfo = labelService.searchPage(label, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
    /**
     * 1. 推荐标签列表 排序通过状态，关注数，数量
     * 2. 有效标签列表 排序通过推荐，关注数，数量
     * 3. 更新关注数
     * 4. 更新使用数
     * 6. 更新推荐
     * 7. 更新状态
     */
}
