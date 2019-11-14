package com.bj.pro.qa.controller;

import com.bj.pro.qa.entity.Problem;
import com.bj.pro.qa.service.ProblemService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", problemService.findAll());
    }
    @GetMapping("/{problemId}")
    public Result findById(@PathVariable("problemId") String id){
        return ResultUtil.success("查询成功", problemService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Problem problem){
        problemService.save(problem);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        problemService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{problemId}")
    public Result delete(@PathVariable String problemId){
        problemService.delete(problemId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{problemId}")
    public Result update(@PathVariable String problemId, @RequestBody Problem problem){
        problem.setId(problemId);
        problemService.update(problem);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Problem problem){
        return ResultUtil.success("查询成功", problemService.search(problem));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Problem problem, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Problem> pageinfo = problemService.searchPage(problem, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    @GetMapping("/newlist/{labelid}/{page}/{size}")
    public Result newPage(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Problem> pageinfo = problemService.newPage(labelid, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    @GetMapping("/hotlist/{labelid}/{page}/{size}")
    public Result hotPage(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Problem> pageinfo = problemService.hotPage(labelid, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    @GetMapping("/waitlist/{labelid}/{page}/{size}")
    public Result waitPage(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Problem> pageinfo = problemService.waitPage(labelid, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
    /**
     * 1. 更新浏览量，点赞数，回复数
     * 2. 更新是否解决
     */
}
