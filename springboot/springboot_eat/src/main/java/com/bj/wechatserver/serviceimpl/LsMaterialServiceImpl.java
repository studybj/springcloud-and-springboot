package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.LsMaterialRepository;
import com.bj.wechatserver.entity.LsMaterial;
import com.bj.wechatserver.service.LsMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class LsMaterialServiceImpl implements LsMaterialService {
    @Autowired
    private LsMaterialRepository lsmaterialRepository;

    @Override
    public void save(LsMaterial material) {
        if(StringUtils.isEmpty(material.getId())){
            String id = UUID.randomUUID().toString().substring(0,5) + new Date().getTime();
            material.setId(id);
            lsmaterialRepository.save(material);
       }else{
            LsMaterial ls = findById(material.getId());
            if(!material.getLocalUrl().equals(ls.getLocalUrl())){
                ls.setLocalUrl(material.getLocalUrl());
            }
            if(!material.getStatus().equals(ls.getStatus())){
                ls.setStatus(material.getStatus());
            }
            lsmaterialRepository.save(ls);
        }
    }
    @Override
    public void save(List<LsMaterial> lists) {
        for(LsMaterial material : lists){
            if(StringUtils.isEmpty(material.getId())){
                String id = UUID.randomUUID().toString().substring(0,5) + new Date().getTime();
                material.setId(id);
            }
        }
        lsmaterialRepository.saveAll(lists);
    }
    @Transactional
    @Override
    public Map update(String[] ids, int updateType) {
        List<LsMaterial> materiallist = lsmaterialRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
            for(LsMaterial material : materiallist){
                if(material.getStatus() == 0){
                    material.setStatus(1);
                }else{
                    material.setStatus(0);
                    //TODO, 注意此处禁用标签，则需将标签下的粉丝移除
                }
            }
            //return WeixinUtil.deleteTag(tag.getId());
        } else if(updateType == 2){//粉丝数增加
            //TODO
        }else if(updateType == 3){//粉丝数减少
            //TODO
        }
        save(materiallist);
        Map map = new HashMap();
        map.put("errcode","0");
        map.put("errmsg","成功");
        return map;
    }

    @Override
    public LsMaterial findById(String id) {
        return lsmaterialRepository.findById(id).get();
    }

    @Override
    public List<LsMaterial> findAll() {
        return lsmaterialRepository.findAll();
    }

    @Override
    public List<LsMaterial> findByParam(LsMaterial material) {
        Specification<LsMaterial> spec = paramOperate(material, null);
        return lsmaterialRepository.findAll(spec);
    }

    @Override
    public List<LsMaterial> findByTagName(String tagname) {
        return null;
    }

    @Override
    public Page<LsMaterial> findAll(Pageable pageable) {
        return lsmaterialRepository.findAll(pageable);
    }

    @Override
    public Page<LsMaterial> findByParam(LsMaterial material, String time, Pageable pageable) {
        Specification<LsMaterial> spec = paramOperate(material, time);
        return lsmaterialRepository.findAll(spec,pageable);
    }

    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            lsmaterialRepository.deleteById(id);
        }
    }

    @Override
    public long count(LsMaterial material, String time) {
        Specification<LsMaterial> spec = paramOperate(material,time);
        return lsmaterialRepository.count(spec);
    }

    private Specification<LsMaterial> paramOperate(LsMaterial material, String time) {
        Specification<LsMaterial> spec = new Specification<LsMaterial>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<LsMaterial> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(material != null){
                    if(material.getType() != null && !"".equals(material.getType())){
                        System.out.println(":=="+material.getType());
                        Expression<String> exp = root.get("type");
                        predicatesList.add(exp.in(material.getType().split(",")));
                    }
                    if(material.getStatus() != null && !"".equals(material.getStatus())){
                        Predicate statusPredicate = cb.equal(root.get("status"), material.getStatus());
                        predicatesList.add(statusPredicate);
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
