package com.justtide.tms.controller;

import com.justtide.tms.common.PageResult;
import com.justtide.tms.common.Result;
import com.justtide.tms.entity.DeviceError;
import com.justtide.tms.service.DeviceErrorService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/deviceError")
public class DeviceErrorController {

    @Autowired
    private DeviceErrorService deviceErrorService;

    @GetMapping

    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", deviceErrorService.findAll());
    }
    @GetMapping("/{deviceErrorId}")
    public Result findById(@PathVariable("deviceErrorId") String id){
        return ResultUtil.success("查询成功", deviceErrorService.findById(Integer.valueOf(id)));
    }

    @PostMapping
    public Result save(@RequestBody DeviceError deviceError){
        deviceErrorService.save(deviceError);
        return ResultUtil.success("添加成功");
    }
    @DeleteMapping
    public Result deleteAll(){
        deviceErrorService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{deviceErrorId}")
    public Result delete(@PathVariable String deviceErrorId){
        deviceErrorService.delete(Integer.valueOf(deviceErrorId));
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{deviceErrorId}")
    public Result update(@PathVariable String deviceErrorId, @RequestBody DeviceError deviceError){
        deviceError.setId(Integer.valueOf(deviceErrorId));
        deviceErrorService.update(deviceError);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody DeviceError deviceError){
        return ResultUtil.success("查询成功", deviceErrorService.search(deviceError));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody DeviceError deviceError, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<DeviceError> pageinfo = deviceErrorService.searchPage(deviceError, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
