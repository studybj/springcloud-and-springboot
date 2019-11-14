package com.bj.pro.gathering.controller;

import com.bj.pro.gathering.entity.Gathering;
import com.bj.pro.gathering.service.GatheringService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", gatheringService.findAll());
    }
    @GetMapping("/{gtheringId}")
    public Result findById(@PathVariable("gtheringId") String id){
        return ResultUtil.success("查询成功", gatheringService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Gathering gthering){
        gatheringService.save(gthering);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        gatheringService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{gtheringId}")
    public Result delete(@PathVariable String gtheringId){
        gatheringService.delete(gtheringId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{gtheringId}")
    public Result update(@PathVariable String gtheringId, @RequestBody Gathering gthering){
        gthering.setId(gtheringId);
        gatheringService.update(gthering);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Gathering gthering){
        return ResultUtil.success("查询成功", gatheringService.search(gthering));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Gathering gthering, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Gathering> pageinfo = gatheringService.searchPage(gthering, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    @GetMapping("/search/hotlist")
    public Result hotlist(){
        return ResultUtil.success("查询成功");
    }

}
