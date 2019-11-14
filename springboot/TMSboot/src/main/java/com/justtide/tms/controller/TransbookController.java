package com.justtide.tms.controller;

import com.justtide.tms.common.Result;
import com.justtide.tms.entity.Transbook;
import com.justtide.tms.service.TransbookService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/transbook")
public class TransbookController {

    @Autowired
    private TransbookService transbookService;

    @GetMapping

    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", transbookService.findAll());
    }
    @GetMapping("/{transbookId}")
    public Result findById(@PathVariable("transbookId") String id){
        return ResultUtil.success("查询成功", transbookService.findById(Integer.valueOf(id)));
    }

    @PostMapping
    public Result save(@RequestBody Transbook transbook){
        transbookService.save(transbook);
        return ResultUtil.success("添加成功");
    }
    @DeleteMapping
    public Result deleteAll(){
        transbookService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{transbookId}")
    public Result delete(@PathVariable String transbookId){
        transbookService.delete(Integer.valueOf(transbookId));
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{transbookId}")
    public Result update(@PathVariable String transbookId, @RequestBody Transbook transbook){
        transbook.setId(Integer.valueOf(transbookId));
        transbookService.update(transbook);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Transbook transbook){
        return ResultUtil.success("查询成功", transbookService.search(transbook));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Transbook transbook, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Transbook> pageinfo = transbookService.searchPage(transbook, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
