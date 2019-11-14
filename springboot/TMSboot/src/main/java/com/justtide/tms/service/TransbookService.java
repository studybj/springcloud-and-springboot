package com.justtide.tms.service;

import com.justtide.tms.entity.Transbook;
import com.justtide.tms.repository.TransbookRepository;
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
public class TransbookService {
    @Autowired
    private TransbookRepository transbookRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Transbook> findAll(){
        return transbookRepository.findAll();
    }
    public Transbook findById(Integer id){
        Transbook transbook = (Transbook) redisTemplate.opsForValue().get("transbook_" + id);
        if (transbook == null){
            transbook = transbookRepository.findById(id).get();
            redisTemplate.opsForValue().set("transbook_" + id, transbook);
        }
        return transbook;
    }
    public void save(Transbook transbook){
        transbookRepository.save(transbook);
    }
    public void update(Transbook transbook){
        redisTemplate.delete("transbook_" + transbook.getId());
        transbookRepository.save(transbook);
    }
    public void deleteAll(){
        redisTemplate.delete("transbook_*");
        transbookRepository.deleteAll();
    }
    public void delete(Integer id){
        redisTemplate.delete("transbook_" + id);
        transbookRepository.deleteById(id);
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Transbook> search(Transbook transbook){
        return transbookRepository.findAll(paramOperate(transbook));
    }

    public Page<Transbook> searchPage(Transbook transbook, Pageable pageable) {
        return transbookRepository.findAll(paramOperate(transbook), pageable);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Transbook> paramOperate(Transbook transbook) {
        Specification<Transbook> spec = new Specification<Transbook>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Transbook> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(transbook != null){
                    if(transbook.getDeviceSn() != null && !"".equals(transbook.getDeviceSn())){
                        Predicate transbookSnnamePredicate = cb.like(root.get("deviceSn"), "%" + transbook.getDeviceSn() + "%");
                        predicatesList.add(transbookSnnamePredicate);
                    }
                    if(transbook.getDeviceType() != null && !"".equals(transbook.getDeviceType())){
                        Predicate transbookTypePredicate = cb.equal(root.get("deviceType"), transbook.getDeviceType());
                        predicatesList.add(transbookTypePredicate);
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
