package com.bj.pro.recruit.controller;

import com.bj.pro.recruit.entity.Recruit;
import com.bj.pro.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return new Result(true, StatusCode.SUCCESS,"查询成功", recruitService.findAll());
    }
    @GetMapping("/{recruitId}")
    public Result findById(@PathVariable("recruitId") String id){
        return new Result(true, StatusCode.SUCCESS,"查询成功", recruitService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Recruit recruit){
        recruitService.save(recruit);
        return new Result(true, StatusCode.SUCCESS,"添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        recruitService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{recruitId}")
    public Result delete(@PathVariable String recruitId){
        recruitService.delete(recruitId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{recruitId}")
    public Result update(@PathVariable String recruitId, @RequestBody Recruit recruit){
        recruit.setId(recruitId);
        recruitService.update(recruit);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Recruit recruit){
        return ResultUtil.success("查询成功", recruitService.search(recruit));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Recruit recruit, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Recruit> pageinfo = recruitService.searchPage(recruit, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    /** 获取推荐列表 */
    @GetMapping("/search/recommend")
    public Result recommendlist(){
        return ResultUtil.success("查询成功", recruitService.recommendlist("2"));
    }
    /** 获取最新列表 */
    @GetMapping("/search/newlist")
    public Result newlist(){
        return ResultUtil.success("查询成功", recruitService.newlist("0"));
    }
}
