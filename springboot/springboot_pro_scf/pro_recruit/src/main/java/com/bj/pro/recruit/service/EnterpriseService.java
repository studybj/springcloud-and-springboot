package com.bj.pro.recruit.service;

import com.bj.pro.recruit.entity.Enterprise;
import com.bj.pro.recruit.repository.EnterpriseRepository;
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
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Enterprise> findAll(){
        return enterpriseRepository.findAll();
    }
    public Enterprise findById(String id){
        Enterprise enterprise = (Enterprise) redisTemplate.opsForValue().get("enterprise_" + id);
        if (enterprise == null){
            enterprise = enterpriseRepository.findById(id).get();
            redisTemplate.opsForValue().set("enterprise_" + id, enterprise);
        }
        return enterprise;
    }
    public void save(Enterprise enterprise){
        enterprise.setId(idWorker.nextId() + "");
        enterpriseRepository.save(enterprise);
    }
    public void update(Enterprise enterprise){
        redisTemplate.delete("enterprise_" + enterprise.getId());
        enterpriseRepository.save(enterprise);
    }
    public void deleteAll(){
        redisTemplate.delete("enterprise_*");
        enterpriseRepository.deleteAll();
    }
    public void delete(String id){
        redisTemplate.delete("enterprise_" + id);
        enterpriseRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Enterprise> search(Enterprise enterprise){
        return enterpriseRepository.findAll(paramOperate(enterprise));
    }

    public Page<Enterprise> searchPage(Enterprise enterprise, Pageable pageable) {
        return enterpriseRepository.findAll(paramOperate(enterprise), pageable);
    }
    /**热门企业*/
    public List<Enterprise> hotlist(String ishot) {
        return enterpriseRepository.findByIshot(ishot);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Enterprise> paramOperate(Enterprise enterprise) {
        Specification<Enterprise> spec = new Specification<Enterprise>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(enterprise != null){
                    if(!StringUtils.isEmpty(enterprise.getName())){
                        Predicate namePredicate = cb.like(root.get("name"), "%" + enterprise.getName() + "%");
                        predicatesList.add(namePredicate);
                    }
                    if(!StringUtils.isEmpty(enterprise.getIshot())){
                        Predicate ishotPredicate = cb.equal(root.get("ishot"), enterprise.getIshot());
                        predicatesList.add(ishotPredicate);
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
