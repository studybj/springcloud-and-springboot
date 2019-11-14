package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.TemplateRepository;
import com.bj.wechatserver.entity.Template;
import com.bj.wechatserver.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public void save(Template template) {
        templateRepository.save(template);
    }

    @Override
    public void save(List<Template> lists) {
        templateRepository.saveAll(lists);
    }

    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            templateRepository.deleteById(id);
        }
    }

    @Override
    public Map update(String[] ids, int updateType) {
        List<Template> list = templateRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
            for(Template tag : list){
                if(tag.getStatus() == 0){
                    tag.setStatus(1);
                }else{
                    tag.setStatus(0);
                }
            }
            //return WeixinUtil.deleteTag(tag.getId());
        } else if(updateType == 2){
            //TODO
        }
        save(list);
        Map map = new HashMap();
        map.put("errcode","0");
        map.put("errmsg","成功");
        return map;
    }

    @Override
    public Template findById(String id) {
        return templateRepository.findById(id).get();
    }

    @Override
    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public List<Template> findByParam(Template template) {
        Specification<Template> spec = paramOperate(template, null);
        return templateRepository.findAll(spec);
    }

    @Override
    public Page<Template> findAll(Pageable pageable) {
        return templateRepository.findAll(pageable);
    }

    @Override
    public Page<Template> findByParam(Template template, String time, Pageable pageable) {
        Specification<Template> spec = paramOperate(template, time);
        return templateRepository.findAll(spec,pageable);
    }

    @Override
    public long count(Template template, String time) {
        Specification<Template> spec = paramOperate(template,time);
        return templateRepository.count(spec);
    }

    private Specification<Template> paramOperate(Template template, String time) {
        Specification<Template> spec = new Specification<Template>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Template> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                /*if(template.getNickname() != null && !"".equals(template.getNickname())){
                    Predicate namePredicate = cb.like(root.get("nickname"), "%" + template.getNickname() + "%");
                    predicatesList.add(namePredicate);
                    *//*Expression<String> exp = root.get("tagid");
                    predicatesList.add(exp.in(info)); // 往in中添加所有id 实现in 查询*//*
                }*/
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
