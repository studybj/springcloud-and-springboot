package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.Account;
import com.bj.wechatserver.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(accountService.findAll(pageRequest).getContent(), accountService.count(null,null));
    }

    @RequestMapping("/listByParam")
    public ResultVo list(Account account, String time, Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(accountService.findByParam(account, time, pageRequest).getContent(), accountService.count(account,time));
    }

    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam String[] ids){
        accountService.delete(ids);
        return ResultVoUtil.success();
    }
    /**
     * @param updateType 更新类型  1 更改状态,2
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ResultVo update(String[] ids, int updateType){
        System.out.println("ids = [" + ids.toString() + "], updateType = [" + updateType + "]");
        Map result = accountService.update(ids,updateType);
        return  ResultVoUtil.result(result);
    }

    @RequestMapping("/save")
    public ResultVo save(Account account){
        accountService.save(account);
        return ResultVoUtil.success();
    }
}
