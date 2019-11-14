package com.justtide.tms.service;

import com.justtide.tms.entity.SystemLog;
import com.justtide.tms.repository.SystemLogRepository;
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
public class SystemLogService {
    @Autowired
    private SystemLogRepository systemLogRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<SystemLog> findAll(){
        return systemLogRepository.findAll();
    }
    public SystemLog findById(Integer id){
        SystemLog systemLog = (SystemLog) redisTemplate.opsForValue().get("systemLog_" + id);
        if (systemLog == null){
            systemLog = systemLogRepository.findById(id).get();
            redisTemplate.opsForValue().set("systemLog_" + id, systemLog);
        }
        return systemLog;
    }
    public void save(SystemLog systemLog){
        systemLogRepository.save(systemLog);
    }
    public void update(SystemLog systemLog){
        redisTemplate.delete("systemLog_" + systemLog.getId());
        systemLogRepository.save(systemLog);
    }
    public void deleteAll(){
        redisTemplate.delete("systemLog_*");
        systemLogRepository.deleteAll();
    }
    public void delete(Integer id){
        redisTemplate.delete("systemLog_" + id);
        systemLogRepository.deleteById(id);
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<SystemLog> search(SystemLog systemLog){
        return systemLogRepository.findAll(paramOperate(systemLog));
    }

    public Page<SystemLog> searchPage(SystemLog systemLog, Pageable pageable) {
        return systemLogRepository.findAll(paramOperate(systemLog), pageable);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<SystemLog> paramOperate(SystemLog systemLog) {
        Specification<SystemLog> spec = new Specification<SystemLog>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<SystemLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(systemLog != null){
                    if(systemLog.getOperate() != null && !"".equals(systemLog.getOperate())){
                        Predicate systemLogSnnamePredicate = cb.like(root.get("operate"), "%" + systemLog.getOperate() + "%");
                        predicatesList.add(systemLogSnnamePredicate);
                    }
                    if(systemLog.getRole() != null && !"".equals(systemLog.getRole())){
                        Predicate systemLogPredicate = cb.equal(root.get("role"), systemLog.getRole());
                        predicatesList.add(systemLogPredicate);
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
