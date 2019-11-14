package com.bj.pro.search.controller;

import com.bj.pro.search.entity.Article;
import com.bj.pro.search.service.ArticleService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtil;

@RestController
@CrossOrigin    //支持跨域
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return ResultUtil.success("添加成功");
    }
    @GetMapping("/{key}/{page}/{size}")
    public Result findByKey(@PathVariable("key") String key, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Article> articleData = articleService.findByKey(key, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(articleData.getTotalElements(), articleData.getContent()));
    }

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", articleService.findAll());
    }
    @GetMapping("/{articleId}")
    public Result findById(@PathVariable("articleId") String id){
        return ResultUtil.success("查询成功", articleService.findById(id));
    }

    @DeleteMapping
    public Result deleteAll(){
        articleService.deleteAll();
        return ResultUtil.success("删除成功");
    }

    @DeleteMapping("/{articleId}")
    public Result delete(@PathVariable String articleId){
        articleService.delete(articleId);
        return ResultUtil.success("删除成功");
    }

    @PutMapping("/{articleId}")
    public Result update(@PathVariable String articleId, @RequestBody Article article){
        article.setId(articleId);
        articleService.update(article);
        return ResultUtil.success("更新成功");
    }

}
