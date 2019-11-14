package com.justtide.tms.controller;

import com.justtide.tms.common.PageResult;
import com.justtide.tms.common.Result;
import com.justtide.tms.entity.DeviceType;
import com.justtide.tms.service.DeviceTypeService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/deviceType")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", deviceTypeService.findAll());
    }
    @GetMapping("/{deviceTypeId}")
    public Result findById(@PathVariable("deviceTypeId") String id){
        return ResultUtil.success("查询成功", deviceTypeService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody DeviceType deviceType){
        deviceTypeService.save(deviceType);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        deviceTypeService.deleteAll();
        return ResultUtil.success("删除成功");
    }
    @DeleteMapping("/batch")
    public Result deleteBatch (@RequestBody DeviceType[] deviceTypes){
        deviceTypeService.deleteBatch(Arrays.asList(deviceTypes));
        return ResultUtil.success("删除成功");
    }
    @DeleteMapping("/{deviceTypeId}")
    public Result delete(@PathVariable String deviceTypeId){
        deviceTypeService.delete(deviceTypeId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{deviceTypeId}")
    public Result update(@PathVariable String deviceTypeId, @RequestBody DeviceType deviceType){
        deviceType.setDeviceTypeId(deviceTypeId);
        deviceTypeService.update(deviceType);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody DeviceType deviceType){
        return ResultUtil.success("查询成功", deviceTypeService.search(deviceType));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody DeviceType deviceType, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<DeviceType> pageinfo = deviceTypeService.searchPage(deviceType, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
