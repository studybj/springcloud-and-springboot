package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.UserTagRepository;
import com.bj.wechatserver.entity.UserTag;
import com.bj.wechatserver.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserTagServiceImpl implements UserTagService {
    @Autowired
    private UserTagRepository userTagRepository;

    @Override
    public void save(UserTag userTag) {
        userTag.setCreateTime(new Date());
        if(userTag.getCount() == null){
            userTag.setCount(0);
        }
        userTagRepository.save(userTag);
    }
    @Override
    public void save(List<UserTag> lists) {
        for(UserTag tag : lists){
            tag.setCount(0);
            tag.setCreateTime(new Date());
        }
        userTagRepository.saveAll(lists);
    }
    @Transactional
    @Override
    public Map update(Integer[] ids, int updateType) {
        List<UserTag> taglist = userTagRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
            for(UserTag tag : taglist){
                if(tag.getStatus() == 0){
                    tag.setStatus(1);
                }else{
                    tag.setStatus(0);
                    //TODO, 注意此处禁用标签，则需将标签下的粉丝移除
                    tag.setCount(0);
                }
            }
            //return WeixinUtil.deleteTag(tag.getId());
        } else if(updateType == 2){//粉丝数增加
            //TODO
        }else if(updateType == 3){//粉丝数减少
            //TODO
        }
        save(taglist);
        Map map = new HashMap();
        map.put("errcode","0");
        map.put("errmsg","成功");
        return map;
    }

    @Override
    public UserTag findById(Integer id) {
        return userTagRepository.findById(id).get();
    }

    @Override
    public List<UserTag> findAll() {
        return userTagRepository.findAll();
    }

    @Override
    public List<UserTag> findByParam(UserTag userTag) {
        Specification<UserTag> spec = paramOperate(userTag, null);
        return userTagRepository.findAll(spec);
    }

    @Override
    public List<UserTag> findByTagName(String tagname) {
        return userTagRepository.findByName(tagname);
    }

    @Override
    public Page<UserTag> findAll(Pageable pageable) {
        return userTagRepository.findAll(pageable);
    }

    @Override
    public Page<UserTag> findByParam(UserTag userTag, String time, Pageable pageable) {
        Specification<UserTag> spec = paramOperate(userTag, time);
        return userTagRepository.findAll(spec,pageable);
    }

    @Override
    public void delete(Integer[] ids) {
        for(Integer id : ids){
            userTagRepository.deleteById(id);
        }
    }

    @Override
    public long count(UserTag userTag, String time) {
        Specification<UserTag> spec = paramOperate(userTag,time);
        return userTagRepository.count(spec);
    }

    private Specification<UserTag> paramOperate(UserTag userTag, String time) {
        Specification<UserTag> spec = new Specification<UserTag>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<UserTag> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(userTag != null){
                    if(userTag.getName() != null && !"".equals(userTag.getName())){
                        Predicate namePredicate = cb.like(root.get("name"), "%" + userTag.getName() + "%");
                        predicatesList.add(namePredicate);
                    }
                    if(userTag.getStatus() != null && !"".equals(userTag.getStatus())){
                        Predicate statusPredicate = cb.equal(root.get("status"), userTag.getStatus());
                        predicatesList.add(statusPredicate);
                    }/*
                    if(userTag.getCount() != null && !"".equals(userTag.getCount())){
                        Predicate countryPredicate = cb.between();root.get("count"), userTag.getCount()
                        predicatesList.add(countryPredicate);
                    }*/
                    if(time != null && !"".equals(time)){
                        String[] times = time.split("到");
                        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try{
                            Predicate timestartPredicate = cb.greaterThanOrEqualTo   (root.get("createTime").as(Date.class), simple.parse(times[0]));
                            Predicate timeendPredicate = cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), simple.parse(times[1]));
                            predicatesList.add(timestartPredicate);
                            predicatesList.add(timeendPredicate);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
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
