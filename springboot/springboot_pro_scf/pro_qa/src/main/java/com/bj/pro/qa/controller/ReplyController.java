package com.bj.pro.qa.controller;

import com.bj.pro.qa.client.BaseJpaClient;
import com.bj.pro.qa.entity.Reply;
import com.bj.pro.qa.service.ReplyService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private BaseJpaClient baseJpaClient;

    @GetMapping("/label/{labelId}")
    public Result findByLabelId(@PathVariable String labelId){
        return baseJpaClient.findById(labelId);
    }


    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return new Result(true, StatusCode.SUCCESS,"查询成功", replyService.findAll());
    }
    @GetMapping("/{replyId}")
    public Result findById(@PathVariable("replyId") String id){
        return new Result(true, StatusCode.SUCCESS,"查询成功", replyService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Reply reply){
        replyService.save(reply);
        return new Result(true, StatusCode.SUCCESS,"添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        replyService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{replyId}")
    public Result delete(@PathVariable String replyId){
        replyService.delete(replyId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{replyId}")
    public Result update(@PathVariable String replyId, @RequestBody Reply reply){
        reply.setId(replyId);
        replyService.update(reply);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Reply reply){
        return ResultUtil.success("查询成功", replyService.search(reply));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Reply reply, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Reply> pageinfo = replyService.searchPage(reply, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
