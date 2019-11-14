package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.UserTagXRepository;
import com.bj.wechatserver.entity.UserInfo;
import com.bj.wechatserver.entity.UserTagX;
import com.bj.wechatserver.service.UserTagXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserTagXServiceImpl implements UserTagXService {
    @Autowired
    private UserTagXRepository userTagXRepository;

    @Override
    public void save(UserTagX userTagX) {
        userTagXRepository.save(userTagX);
    }

    @Override
    public void save(List<UserTagX> lists) {
        userTagXRepository.saveAll(lists);
    }

    @Override
    public List<UserTagX> findByParam(UserTagX tagX, int type) {
        if(type == 1){//通过userid查询
            return userTagXRepository.findByUserid(tagX.getUserid());
        }else if(type == 2){//通过tagid查询
            return userTagXRepository.findByTagid(tagX.getTagid());
        }else if(type == 3){//通过userid和tagid查询
            return userTagXRepository.findByUseridAndTagid(tagX.getUserid(),tagX.getTagid());
        }
        return null;
    }

    @Override
    public List<String> findByParam(String tagidlist) {
        Specification<UserTagX> spec = paramOperate(tagidlist);
        List<UserTagX> lists =  userTagXRepository.findAll(spec);
        List<String> useridlist = new ArrayList<>();
        for(UserTagX tagX : lists){
            useridlist.add(tagX.getUserid());
        }
        return useridlist;
    }
    private Specification<UserTagX> paramOperate(String info) {
        Specification<UserTagX> spec = new Specification<UserTagX>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<UserTagX> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(info != null && !"".equals(info)){
                    Expression<String> exp = root.get("tagid");
                    predicatesList.add(exp.in(info)); // 往in中添加所有id 实现in 查询
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
