package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.KeyReplyRepository;
import com.bj.wechatserver.dao.UserInfoRepository;
import com.bj.wechatserver.entity.KeyReply;
import com.bj.wechatserver.service.KeyReplyService;
import com.bj.wechatserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class KeyReplyServiceImpl implements KeyReplyService {
    @Autowired
    private KeyReplyRepository keyReplyRepository;

    @Override
    public List<KeyReply> findAll() {
        return keyReplyRepository.findAll();
    }

    @Override
    public List<KeyReply> findByParam(KeyReply keyReply) {
        return null;
    }

    @Override
    public Page<KeyReply> findAll(Pageable pageable) {
        return keyReplyRepository.findAll(pageable);
    }

    @Override
    public Page<KeyReply> findByParam(KeyReply keyReply, Pageable pageable) {
        Specification<KeyReply> spec = paramOperate(keyReply);
        return keyReplyRepository.findAll(spec,pageable);
    }

    @Override
    public KeyReply findByKey(String key, Integer status) {
        return keyReplyRepository.findByRkeyAndStatus(key, status);
    }

    @Override
    public List<KeyReply> findByIsFirstAndStatus(Integer first, Integer status) {
        return keyReplyRepository.findByIsfirstAndStatus(first,status);
    }

    @Override
    public KeyReply findByKey(String key, Integer first, Integer status) {
        List<KeyReply> list = findByIsFirstAndStatus(first,status);
        for(KeyReply kreply : list){
            if(kreply.getRkey().equals(key))
                return kreply;
        }
        return null;
    }

    @Transactional
    @Override
    public void save(KeyReply keyReply) {
        if("1".equals(keyReply.getRtype())){
            if(StringUtils.isEmpty(keyReply.getId())){
                String id = UUID.randomUUID().toString().substring(0,10) + new Date().getTime();
                keyReply.setId(id);
            }
            keyReplyRepository.save(keyReply);
        }
    }
    @Transactional
    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            keyReplyRepository.deleteById(id);
        }
    }

    @Override
    public long count(KeyReply keyReply) {
        Specification<KeyReply> spec = paramOperate(keyReply);
        return keyReplyRepository.count(spec);
    }

    private Specification<KeyReply> paramOperate(KeyReply keyReply){
        Specification<KeyReply> spec = new Specification<KeyReply>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<KeyReply> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(keyReply != null){
                    if(keyReply.getIsfirst() != null){
                        Predicate firstPredicate = cb.equal(root.get("isfirst"), keyReply.getIsfirst());
                        predicatesList.add(firstPredicate);
                    }
                    if(keyReply.getRtype() != null && !"".equals(keyReply.getRtype())){
                        Expression<Integer> exp = root.get("rtype");
                        predicatesList.add(exp.in(keyReply.getRtype())); // 往in中添加所有id 实现in 查询
                    }
                    if(keyReply.getStatus() != null){
                        Predicate statusPredicate = cb.equal(root.get("status"), keyReply.getStatus());
                        predicatesList.add(statusPredicate);
                    }
                    if(keyReply.getRkey() != null && !"".equals(keyReply.getRkey())){
                        Predicate rkeyPredicate = cb.equal(root.get("rkey"), keyReply.getRkey());
                        predicatesList.add(rkeyPredicate);
                    }
                }
                if (predicatesList.size() != 0) {
                    Predicate[] p = new Predicate[predicatesList.size()];
                    return cb.and(predicatesList.toArray(p));
                } else {
                    return null;
                }
            }
        };
        return spec;
    }
}
