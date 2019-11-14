package com.justtide.tms.controller;

import com.justtide.tms.common.PageResult;
import com.justtide.tms.common.Result;
import com.justtide.tms.entity.Version;
import com.justtide.tms.service.VersionService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/version")
public class VersionController {

    @Autowired
    private VersionService versionService;

    @GetMapping
    public Result findAll() {
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", versionService.findAll());
    }

    @GetMapping("/{versionId}")
    public Result findById(@PathVariable("versionId") String id) {
        return ResultUtil.success("查询成功", versionService.findById(Integer.valueOf(id)));
    }

    @PostMapping
    public Result save(@RequestBody Version version) {
        versionService.save(version);
        return ResultUtil.success("添加成功");
    }
    @DeleteMapping
    public Result deleteAll() {
        versionService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{versionId}")
    public Result delete(@PathVariable String versionId) {
        versionService.delete(Integer.valueOf(versionId));
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{versionId}")
    public Result update(@PathVariable String versionId, @RequestBody Version version) {
        version.setId(Integer.valueOf(versionId));
        versionService.update(version);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Version version) {
        return ResultUtil.success("查询成功", versionService.search(version));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Version version, @PathVariable int page, @PathVariable int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Version> pageinfo = versionService.searchPage(version, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
