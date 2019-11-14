package com.justtide.tms.controller;

import com.justtide.tms.common.Result;
import com.justtide.tms.entity.WhiteList;
import com.justtide.tms.service.WhiteListService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/whiteList")
public class WhiteListController {

    @Autowired
    private WhiteListService whiteListService;

    @GetMapping

    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", whiteListService.findAll());
    }
    @GetMapping("/{whiteListId}")
    public Result findById(@PathVariable("whiteListId") String id){
        return ResultUtil.success("查询成功", whiteListService.findById(Integer.valueOf(id)));
    }

    @PostMapping
    public Result save(@RequestBody WhiteList whiteList){
        whiteListService.save(whiteList);
        return ResultUtil.success("添加成功");
    }
    @DeleteMapping
    public Result deleteAll(){
        whiteListService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{whiteListId}")
    public Result delete(@PathVariable String whiteListId){
        whiteListService.delete(Integer.valueOf(whiteListId));
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{whiteListId}")
    public Result update(@PathVariable String whiteListId, @RequestBody WhiteList whiteList){
        whiteList.setId(Integer.valueOf(whiteListId));
        whiteListService.update(whiteList);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody WhiteList whiteList){
        return ResultUtil.success("查询成功", whiteListService.search(whiteList));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody WhiteList whiteList, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<WhiteList> pageinfo = whiteListService.searchPage(whiteList, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
