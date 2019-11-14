package com.bj.pro.article.controller;

import com.bj.pro.article.entity.Article;
import com.bj.pro.article.service.ArticleService;
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

    @GetMapping
    public Result findAll(){
        //service 获取数据，填充到结果集
        return ResultUtil.success("查询成功", articleService.findAll());
    }
    @GetMapping("/{articleId}")
    public Result findById(@PathVariable("articleId") String id){
        return ResultUtil.success("查询成功", articleService.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return ResultUtil.success("添加成功");
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

    @PostMapping("/search")
    public Result search(@RequestBody Article article){
        return ResultUtil.success("查询成功", articleService.search(article));
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Article article, @PathVariable int page, @PathVariable int size){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<Article> pageinfo = articleService.searchPage(article, pageRequest);
        return ResultUtil.success("查询成功", ResultUtil.pageData(pageinfo.getTotalElements(), pageinfo.getContent()));
    }

    /**
     * 更新审核状态
     * @param state 为1表示审核通过，为2表示审核未通过
     * @param articleId
     * @return
     */
    @PutMapping("/examine/{articleId}/{state}")
    public Result examine(@PathVariable String state, @PathVariable String articleId){
        articleService.updatestate(state, articleId);
        return ResultUtil.success("更新成功");
    }
    /**
     * 更新点赞数
     * @param num 为1表示点赞，为-1表示取消点赞
     * @param articleId
     * @return
     */
    @PutMapping("/thumbup/{articleId}/{num}")
    public Result thumbup(@PathVariable int num, @PathVariable String articleId){
        articleService.updateThumbup(num, articleId);
        return ResultUtil.success("更新成功");
    }
}
