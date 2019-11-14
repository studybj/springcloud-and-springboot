package com.justtide.tms.service;

import com.justtide.tms.entity.WhiteList;
import com.justtide.tms.repository.WhiteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WhiteListService {
    @Autowired
    private WhiteListRepository whiteListRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<WhiteList> findAll(){
        return whiteListRepository.findAll();
    }
    public WhiteList findById(Integer id){
        WhiteList whiteList = (WhiteList) redisTemplate.opsForValue().get("whiteList_" + id);
        if (whiteList == null){
            whiteList = whiteListRepository.findById(id).get();
            redisTemplate.opsForValue().set("whiteList_" + id, whiteList);
        }
        return whiteList;
    }
    public void save(WhiteList whiteList){
        whiteListRepository.save(whiteList);
    }
    public void update(WhiteList whiteList){
        redisTemplate.delete("whiteList_" + whiteList.getId());
        whiteListRepository.save(whiteList);
    }
    public void deleteAll(){
        redisTemplate.delete("whiteList_*");
        whiteListRepository.deleteAll();
    }
    public void delete(Integer id){
        redisTemplate.delete("whiteList_" + id);
        whiteListRepository.deleteById(id);
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<WhiteList> search(WhiteList whiteList){
        return whiteListRepository.findAll(paramOperate(whiteList));
    }

    public Page<WhiteList> searchPage(WhiteList whiteList, Pageable pageable) {
        return whiteListRepository.findAll(paramOperate(whiteList), pageable);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<WhiteList> paramOperate(WhiteList whiteList) {
        Specification<WhiteList> spec = new Specification<WhiteList>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<WhiteList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(whiteList != null){
                    if(whiteList.getDeviceSn() != null && !"".equals(whiteList.getDeviceSn())){
                        Predicate whiteListSnPredicate = cb.like(root.get("deviceSn"), "%" + whiteList.getDeviceSn() + "%");
                        predicatesList.add(whiteListSnPredicate);
                    }
                    if(whiteList.getDeviceType() != null && !"".equals(whiteList.getDeviceType())){
                        Predicate whiteListPredicate = cb.equal(root.get("deviceType"), whiteList.getDeviceType());
                        predicatesList.add(whiteListPredicate);
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
