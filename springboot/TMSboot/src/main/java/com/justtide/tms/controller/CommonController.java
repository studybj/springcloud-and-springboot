package com.justtide.tms.controller;
import com.justtide.tms.common.Result;
import com.justtide.tms.constant.Constants;
import com.justtide.tms.util.ResultUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping(value = "/common")
public class CommonController {
    @GetMapping("/version")
    public Result getVersionConstantList(){
        return ResultUtil.success("查询成功", Constants.toJson());
    }
}
