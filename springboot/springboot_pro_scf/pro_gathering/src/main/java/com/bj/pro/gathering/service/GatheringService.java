package com.bj.pro.gathering.service;

import com.bj.pro.gathering.entity.Gathering;
import com.bj.pro.gathering.repository.GatheringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
public class GatheringService{
    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private IdWorker idWorker;

    //-----------------------------------基本操作--------------------------------------------------
    public List<Gathering> findAll(){
        return gatheringRepository.findAll();
    }
    @Cacheable(value = "gathering", key = "#id")
    public Gathering findById(String id){
        return gatheringRepository.findById(id).get();
    }
    public void save(Gathering gathering){
        gathering.setId(idWorker.nextId() + "");
        gatheringRepository.save(gathering);
    }
    @CacheEvict(value = "gathering", key = "#gathering.id")
    public void update(Gathering gathering){
        gatheringRepository.save(gathering);
    }
    public void deleteAll(){
        gatheringRepository.deleteAll();
    }
    @CacheEvict(value = "gathering", key = "#id")
    public void delete(String id){
        gatheringRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Gathering> search(Gathering gathering){
        return gatheringRepository.findAll(paramOperate(gathering));
    }

    public Page<Gathering> searchPage(Gathering gathering, Pageable pageable) {
        return gatheringRepository.findAll(paramOperate(gathering), pageable);
    }
    @CacheEvict(value = "gathering", key = "#id")
    public void updatestate(String state, String id){
        gatheringRepository.updateState(state, id);
    }
    @CacheEvict(value = "gathering", key = "#id")
    public void updateThumbup(int num, String id){
        gatheringRepository.updateThumbup(num, id);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Gathering> paramOperate(Gathering gathering) {
        Specification<Gathering> spec = new Specification<Gathering>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(gathering != null){
                    if(gathering.getName() != null && !"".equals(gathering.getName())){
                        Predicate titlePredicate = cb.like(root.get("name"), "%" + gathering.getName() + "%");
                        predicatesList.add(titlePredicate);
                    }
                    if(gathering.getState() != null && !"".equals(gathering.getState())){
                        Predicate statePredicate = cb.equal(root.get("state"), gathering.getState());
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
