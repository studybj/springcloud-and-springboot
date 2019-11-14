package com.justtide.tms.controller;

import com.justtide.tms.common.Result;
import com.justtide.tms.entity.SystemLog;
import com.justtide.tms.service.SystemLogService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/systemLog")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    @GetMapping

    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", systemLogService.findAll());
    }
    @GetMapping("/{systemLogId}")
    public Result findById(@PathVariable("systemLogId") String id){
        return ResultUtil.success("查询成功", systemLogService.findById(Integer.valueOf(id)));
    }

    @PostMapping
    public Result save(@RequestBody SystemLog systemLog){
        systemLogService.save(systemLog);
        return ResultUtil.success("添加成功");
    }
    @DeleteMapping
    public Result deleteAll(){
        systemLogService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{systemLogId}")
    public Result delete(@PathVariable String systemLogId){
        systemLogService.delete(Integer.valueOf(systemLogId));
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{systemLogId}")
    public Result update(@PathVariable String systemLogId, @RequestBody SystemLog systemLog){
        systemLog.setId(Integer.valueOf(systemLogId));
        systemLogService.update(systemLog);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody SystemLog systemLog){
        return ResultUtil.success("查询成功", systemLogService.search(systemLog));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody SystemLog systemLog, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<SystemLog> pageinfo = systemLogService.searchPage(systemLog, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
