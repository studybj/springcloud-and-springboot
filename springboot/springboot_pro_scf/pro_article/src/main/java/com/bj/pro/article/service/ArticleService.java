package com.bj.pro.article.service;

import com.bj.pro.article.entity.Article;
import com.bj.pro.article.repository.ArticleRepository;
import exception.OwnRuntimeException;
import exception.ProUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Article> findAll(){
        return articleRepository.findAll();
    }
    public Article findById(String id){
        //先从缓存中读取，若没有则查询数据库
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        if(article == null){
            article = articleRepository.findById(id).get();
            redisTemplate.opsForValue().set("article_" + id, article);
        }
        return article;
    }
    public void save(Article article){
        article.setId(idWorker.nextId() + "");
        articleRepository.save(article);
    }
    public void update(Article article){
        redisTemplate.delete("article_" + article.getId());
        articleRepository.save(article);
        //redisTemplate.opsForValue().set("article_" + article.getId(), article);
    }
    public void deleteAll(){
        redisTemplate.delete("article_*");
        articleRepository.deleteAll();
    }
    public void delete(String id){
        redisTemplate.delete("article_" + id);
        articleRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Article> search(Article article){
        return articleRepository.findAll(paramOperate(article));
    }

    public Page<Article> searchPage(Article article, Pageable pageable) {
        return articleRepository.findAll(paramOperate(article), pageable);
    }

    public void updatestate(String state, String id){
        redisTemplate.delete("article_" + id);
        if (!"1".equals(state) && !"2".equals(state)) {
            throw new OwnRuntimeException("参数错误");
        }
        articleRepository.updateState(state, id);
    }
    public void updateThumbup(int num, String id){
        redisTemplate.delete("article_" + id);
        if (num != 1 && num != -1) {
            try {
                throw new ProUserException("参数错误");
            } catch (ProUserException e) {
                e.printStackTrace();
            }
        }
        articleRepository.updateThumbup(num, id);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Article> paramOperate(Article article) {
        Specification<Article> spec = new Specification<Article>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(article != null){
                    if(article.getTitle() != null && !"".equals(article.getTitle())){
                        Predicate titlePredicate = cb.like(root.get("title"), "%" + article.getTitle() + "%");
                        predicatesList.add(titlePredicate);
                    }
                    if(article.getColumnid() != null && !"".equals(article.getColumnid())){
                        Predicate columnidPredicate = cb.equal(root.get("columnid"), article.getColumnid());
                        predicatesList.add(columnidPredicate);
                    }
                    if(article.getColumnid() != null && !"".equals(article.getColumnid())){
                        Predicate channelidPredicate = cb.equal(root.get("channelid"), article.getColumnid());
                        predicatesList.add(channelidPredicate);
                    }
                    if(article.getUserid() != null && !"".equals(article.getUserid())){
                        Predicate useridPredicate = cb.equal(root.get("userid"), article.getUserid());
                        predicatesList.add(useridPredicate);
                    }
                    if(article.getIspublic() != null && !"".equals(article.getIspublic())){
                        Predicate ispublicPredicate = cb.equal(root.get("ispublic"), article.getIspublic());
                        predicatesList.add(ispublicPredicate);
                    }
                    if(article.getIstop() != null && !"".equals(article.getIstop())){
                        Predicate istopPredicate = cb.equal(root.get("istop"), article.getIstop());
                        predicatesList.add(istopPredicate);
                    }
                    if(article.getType() != null && !"".equals(article.getType())){
                        Predicate typePredicate = cb.equal(root.get("type"), article.getType());
                        predicatesList.add(typePredicate);
                    }
                    if(article.getState() != null && !"".equals(article.getState())){
                        Predicate statePredicate = cb.equal(root.get("state"), article.getState());
                        predicatesList.add(statePredicate);
                    }
                }
                if (predicatesList.size() != 0) {
                    Predicate[] p = new Predicate[predicatesList.size()];
                    return cb.and(predicatesList.toArray(p));
                }
                return null;
            }
        };
        return spec;
    }
}
