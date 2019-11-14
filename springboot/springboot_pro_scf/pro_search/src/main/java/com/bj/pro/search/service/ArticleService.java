package com.bj.pro.search.service;

import com.bj.pro.search.entity.Article;
import com.bj.pro.search.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private IdWorker idWorker;

    //-----------------------------------基本操作--------------------------------------------------
    public List<Article> findAll(){
        return (List<Article>) articleRepository.findAll();
    }
    public Article findById(String id){
        return articleRepository.findById(id).get();
    }
    public void save(Article article){
        article.setId(idWorker.nextId() + "");
        articleRepository.save(article);
    }
    public void update(Article article){
        articleRepository.save(article);
    }
    public void deleteAll(){
        articleRepository.deleteAll();
    }
    public void delete(String id){
        articleRepository.deleteById(id);
    }
    //-----------------------------------以上基本操作 用来测试，实际生产中对于增删改操作通过logstash来同步数据到索引--------------------------------------------------
    //-----------------------------------条件查询操作---主要使用这个方法-----------------------------------------------
    public Page<Article> findByKey(String key, Pageable pageable) {
        return articleRepository.findByTitleOrContentLike(key, key, pageable);
    }
}
