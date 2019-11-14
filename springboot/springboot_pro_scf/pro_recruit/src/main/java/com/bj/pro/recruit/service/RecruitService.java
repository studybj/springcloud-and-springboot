package com.bj.pro.recruit.service;

import com.bj.pro.recruit.entity.Recruit;
import com.bj.pro.recruit.repository.RecruitRepository;
import org.apache.commons.lang3.StringUtils;
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
public class RecruitService {
    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Recruit> findAll(){
        return recruitRepository.findAll();
    }
    public Recruit findById(String id){
        Recruit recruit = (Recruit) redisTemplate.opsForValue().get("recruit_" + id);
        if (recruit == null){
            recruit = recruitRepository.findById(id).get();
            redisTemplate.opsForValue().set("recruit_" + id, recruit);
        }
        return recruit;
    }
    public void save(Recruit recruit){
        recruit.setId(idWorker.nextId() + "");
        recruitRepository.save(recruit);
    }
    public void update(Recruit recruit){
        redisTemplate.delete("recruit_" + recruit.getId());
        recruitRepository.save(recruit);
    }
    public void deleteAll(){
        redisTemplate.delete("recruit_*");
        recruitRepository.deleteAll();
    }
    public void delete(String id){
        redisTemplate.delete("recruit_" + id);
        recruitRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Recruit> search(Recruit recruit){
        return recruitRepository.findAll(paramOperate(recruit));
    }

    public Page<Recruit> searchPage(Recruit recruit, Pageable pageable) {
        return recruitRepository.findAll(paramOperate(recruit), pageable);
    }

    public List<Recruit> recommendlist(String state){
        return recruitRepository.findByState(state);
    }

    public List<Recruit> newlist(String state) {
        return recruitRepository.findByStateNotOrderByCreatetimeDesc(state);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Recruit> paramOperate(Recruit recruit) {
        Specification<Recruit> spec = new Specification<Recruit>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(recruit != null){
                    if(!StringUtils.isEmpty(recruit.getJobname())){
                        Predicate jobnamePredicate = cb.like(root.get("jobname"), "%" + recruit.getJobname() + "%");
                        predicatesList.add(jobnamePredicate);
                    }
                    if(!StringUtils.isEmpty(recruit.getState())){
                        Predicate statePredicate = cb.equal(root.get("state"), recruit.getState());
                        predicatesList.add(statePredicate);
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
