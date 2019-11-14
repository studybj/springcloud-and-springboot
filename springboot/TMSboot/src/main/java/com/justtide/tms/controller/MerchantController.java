package com.justtide.tms.controller;

import com.justtide.tms.common.PageResult;
import com.justtide.tms.common.Result;
import com.justtide.tms.entity.Merchant;
import com.justtide.tms.service.MerchantService;
import com.justtide.tms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", merchantService.findAll());
    }
    @GetMapping("/{merchantId}")
    public Result findById(@PathVariable("merchantId") String id){
        return ResultUtil.success("查询成功", merchantService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Merchant merchant){
        merchantService.save(merchant);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        merchantService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{merchantId}")
    public Result delete(@PathVariable String merchantId){
        merchantService.delete(merchantId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{merchantId}")
    public Result update(@PathVariable String merchantId, @RequestBody Merchant merchant){
        merchant.setMerchantNo(merchantId);
        merchantService.update(merchant);
        return ResultUtil.success("更新成功");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Merchant merchant){
        return ResultUtil.success("查询成功", merchantService.search(merchant));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Merchant merchant, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Merchant> pageinfo = merchantService.searchPage(merchant, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }
}
