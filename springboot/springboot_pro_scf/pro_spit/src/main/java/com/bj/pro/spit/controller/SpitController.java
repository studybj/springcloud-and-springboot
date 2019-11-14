package com.bj.pro.spit.controller;

import com.bj.pro.spit.entity.Spit;
import com.bj.pro.spit.service.SpitService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", spitService.findAll());
    }
    @GetMapping("/{spitId}")
    public Result findById(@PathVariable("spitId") String id){
        return ResultUtil.success("查询成功", spitService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return ResultUtil.success("添加成功");
    }

    @DeleteMapping
    public Result deleteAll(){
        spitService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.delete(spitId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return ResultUtil.success("更新成功");
    }

    @GetMapping("/commenet/{partentid}/{page}/{size}")
    public Result findByPartentid(@PathVariable String partentid, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Spit> pageinfo = spitService.findByPartentid(partentid, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    @PutMapping("/thumbup/{spitid}/{flag}")
    public Result thumbup(@PathVariable String spitid, boolean flag){
        //判断当前用户是否已点赞，当前用户未进行认证，暂时先写死userid
//        String userid = "123";
//        if(redisTemplate.opsForValue().get("thumbup_" + userid) != null){
//            return ResultUtil.error("不能重复点赞");
//        }
        if(flag){
            spitService.update(1, spitid, "thumbup");
        }else {
            spitService.update(-1, spitid, "thumbup");
        }
//       redisTemplate.opsForValue().set("thumbup_" + userid, "1");
        return ResultUtil.success("点赞成功");
    }
}
