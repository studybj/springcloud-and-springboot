package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import com.bj.wechatserver.entity.KeyReply;
import com.bj.wechatserver.service.KeyReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/keyreply")
@Slf4j
public class KeyReplyController {
    @Autowired
    private KeyReplyService keyReplyService;
    @RequestMapping("/list")
    public ResultVo list(Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(keyReplyService.findAll(pageRequest).getContent(),keyReplyService.count(null));
    }
    @RequestMapping("/listByParam")
    public ResultVo list(KeyReply keyReply,Integer page, Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1,limit);
        return ResultVoUtil.success(keyReplyService.findByParam(keyReply,pageRequest).getContent(),keyReplyService.count(keyReply));
    }
    @RequestMapping("/save")
    public ResultVo save(KeyReply keyReply){
        keyReplyService.save(keyReply);
        return ResultVoUtil.success();
    }
    @RequestMapping("/delete")
    public ResultVo delete(@RequestParam String[] keyReplyId){
        keyReplyService.delete(keyReplyId);
        return ResultVoUtil.success();
    }
    @RequestMapping("/existInfo")
    public @ResponseBody ResultVo existInfo(KeyReply keyReply){
        System.out.println(keyReply);
        if(keyReply.getRkey() == null || "".equals(keyReply.getRkey().trim())){
            List<KeyReply> lists = keyReplyService.findByIsFirstAndStatus(keyReply.getIsfirst(),keyReply.getStatus());
            if(lists.size() > 0)
                return ResultVoUtil.error(-1,"系统中已存在首次回复并启用的记录");
            return ResultVoUtil.success();
        }else{
            KeyReply kreply = keyReplyService.findByKey(keyReply.getRkey().trim(),keyReply.getIsfirst(),keyReply.getStatus());
            if(kreply != null)
                return ResultVoUtil.error(-1,"系统中已存在该关键字并启用的记录");
            return ResultVoUtil.success();
        }
    }
}
