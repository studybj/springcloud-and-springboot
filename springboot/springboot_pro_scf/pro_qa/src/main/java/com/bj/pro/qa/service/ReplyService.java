package com.bj.pro.qa.service;

import com.bj.pro.qa.entity.Reply;
import com.bj.pro.qa.repository.ReplyRepository;
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
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Reply> findAll(){
        return replyRepository.findAll();
    }
    public Reply findById(String id){
        //先从缓存中读取，若没有则查询数据库
        Reply reply = (Reply) redisTemplate.opsForValue().get("reply_" + id);
        if(reply == null){
            reply = replyRepository.findById(id).get();
            redisTemplate.opsForValue().set("reply_" + id, reply);
        }
        return reply;
    }
    public void save(Reply reply){
        reply.setId(idWorker.nextId() + "");
        replyRepository.save(reply);
    }
    public void update(Reply reply){
        redisTemplate.delete("reply_" + reply.getId());
        replyRepository.save(reply);
    }
    public void deleteAll(){
        redisTemplate.delete("reply_*");
        replyRepository.deleteAll();
    }
    public void delete(String id){
        redisTemplate.delete("reply_" + id);
        replyRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Reply> search(Reply reply){
        return replyRepository.findAll(paramOperate(reply));
    }

    public Page<Reply> searchPage(Reply reply, Pageable pageable) {
        return replyRepository.findAll(paramOperate(reply), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Reply> paramOperate(Reply reply) {
        Specification<Reply> spec = new Specification<Reply>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(reply != null){
                    if(reply.getContent() != null && !"".equals(reply.getContent())){
                        Predicate contentPredicate = cb.like(root.get("content"), "%" + reply.getContent() + "%");
                        predicatesList.add(contentPredicate);
                    }
                    if(reply.getNickname() != null && !"".equals(reply.getNickname())){
                        Predicate nicknamePredicate = cb.equal(root.get("nickname"), reply.getNickname());
                        predicatesList.add(nicknamePredicate);
                    }
//                    if(userInfo.getSubscribe_scene() != null && !"".equals(userInfo.getSubscribe_scene())){
//                        Expression<Integer> exp = root.get("subscribe_scene");
//                        predicatesList.add(exp.in(userInfo.getSubscribe_scene())); // 往in中添加所有id 实现in 查询
//                    }
//                    if(userInfo.getTagid_list() != null && !"".equals(userInfo.getTagid_list())){//TODO
//                        //Predicate nicknamePredicate = cb.like(root.get("nickname"), "%" + userInfo.getNickname() + "%");
//                        //predicatesList.add(nicknamePredicate);
//                        List<String> useridlist = tagService.findByParam(userInfo.getTagid_list());
//                        if(useridlist != null && useridlist.size() != 0) {
//                            Expression<String> exp = root.get("id");
//                            predicatesList.add(exp.in(useridlist)); // 往in中添加所有id 实现in 查询
//                        }
//                    }
//                    if(time != null && !"".equals(time)){
//                        String[] times = time.split("到");
//                        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        try{
//                            Predicate timestartPredicate = cb.greaterThanOrEqualTo   (root.get("subscribe_time").as(Date.class), simple.parse(times[0]));
//                            Predicate timeendPredicate = cb.lessThanOrEqualTo(root.get("subscribe_time").as(Date.class), simple.parse(times[1]));
//                            predicatesList.add(timestartPredicate);
//                            predicatesList.add(timeendPredicate);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
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
