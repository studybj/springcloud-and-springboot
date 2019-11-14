package com.justtide.tms.controller;

import com.justtide.tms.common.Result;
import com.justtide.tms.entity.Device;
import com.justtide.tms.service.DeviceService;
import com.justtide.tms.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Api(tags = "终端管理相关接口")
@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ApiOperation("根据终端id获取终端信息")
    @GetMapping("/{deviceId}")
    public Result findById(@PathVariable("deviceId") String id){
        return ResultUtil.success("查询成功", deviceService.findById(Integer.valueOf(id)));
    }

    @ApiOperation("获取所有终端信息")
    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", deviceService.findAll());
    }

    @ApiOperation("根据条件获取终端信息")
    @PostMapping("/search")
    public Result search(@RequestBody Device device){
        return ResultUtil.success("查询成功", deviceService.search(device));
    }

    @ApiOperation("根据条件获取终端信息并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "size", value = "页记录数")
    })
    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Device device, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Device> pageinfo = deviceService.searchPage(device, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    @ApiOperation("添加终端信息")
    @PostMapping
    public Result save(@RequestBody Device device){
        deviceService.save(device);
        return ResultUtil.success("添加成功");
    }
    @ApiOperation("根据所属商户批量添加终端信息")
    @ApiImplicitParam(name = "merchantNo", value = "商户id")
    @PostMapping("/upload/{merchantNo}")
    public Result batchSave(@RequestParam("file") MultipartFile file, @PathVariable String merchantNo){
        if (file.isEmpty()) {
            return ResultUtil.error("上传失败，请选择文件");
        }
        deviceService.batchSave(file);
        return ResultUtil.success("上传并解析成功");
    }

    @ApiOperation("根据终端id更新终端信息")
    @ApiImplicitParam(name = "deviceId", value = "终端id")
    @PutMapping("/{deviceId}")
    public Result update(@PathVariable String deviceId, @RequestBody Device device){
        device.setId(Integer.valueOf(deviceId));
        deviceService.update(device);
        return ResultUtil.success("更新成功");
    }

    @ApiOperation("删除全部终端信息")
    @DeleteMapping
    public Result deleteAll(){
        deviceService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @ApiOperation("根据终端id删除终端信息")
    @ApiImplicitParam(name = "deviceId", value = "终端id")
    @DeleteMapping("/{deviceId}")
    public Result delete(@PathVariable String deviceId){
        deviceService.delete(Integer.valueOf(deviceId));
        return ResultUtil.success("删除成功");
    }
}
