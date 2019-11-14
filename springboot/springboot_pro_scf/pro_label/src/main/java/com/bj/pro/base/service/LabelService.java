package com.bj.pro.base.service;

import com.bj.pro.base.entity.Label;
import com.bj.pro.base.repository.LabelRepository;
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
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Label> findAll(){
        return labelRepository.findAll();
    }
    public Label findById(String id){
        Label label = (Label) redisTemplate.opsForValue().get("label_" + id);
        if (label == null){
            label = labelRepository.findById(id).get();
            redisTemplate.opsForValue().set("label_" + id, label);
        }
        return label;
    }
    public void save(Label label){
        label.setId(idWorker.nextId() + "");
        label.setCount(0l);
        labelRepository.save(label);
    }
    public void update(Label label){
        redisTemplate.delete("label_" + label.getId());
        labelRepository.save(label);
    }
    public void deleteAll(){
        redisTemplate.delete("label_*");
        labelRepository.deleteAll();
    }
    public void delete(String id){
        redisTemplate.delete("label_" + id);
        labelRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Label> search(Label label){
        return labelRepository.findAll(paramOperate(label));
    }

    public Page<Label> searchPage(Label label, Pageable pageable) {
        return labelRepository.findAll(paramOperate(label), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Label> paramOperate(Label label) {
        Specification<Label> spec = new Specification<Label>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(label != null){
                    if(!StringUtils.isEmpty(label.getName())){//label.getName() != null && !"".equals(label.getName())
                        Predicate namePredicate = cb.like(root.get("name"), "%" + label.getName() + "%");
                        predicatesList.add(namePredicate);
                    }
                    if(!StringUtils.isEmpty(label.getState())){
                        Predicate statePredicate = cb.equal(root.get("state"), label.getState());
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
