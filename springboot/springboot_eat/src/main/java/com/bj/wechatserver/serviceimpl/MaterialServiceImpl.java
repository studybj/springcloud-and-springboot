package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.MaterialRepository;
import com.bj.wechatserver.entity.Material;
import com.bj.wechatserver.entity.UserTag;
import com.bj.wechatserver.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public void save(Material material) {
        if(StringUtils.isEmpty(material.getId())){
            String id = UUID.randomUUID().toString().substring(0,5) + new Date().getTime();
            material.setId(id);
       }
        materialRepository.save(material);
    }
    @Override
    public void save(List<Material> lists) {
        for(Material material : lists){
            if(StringUtils.isEmpty(material.getId())){
                String id = UUID.randomUUID().toString().substring(0,5) + new Date().getTime();
                material.setId(id);
            }
        }
        materialRepository.saveAll(lists);
    }
    @Transactional
    @Override
    public Map update(String[] ids, int updateType) {
        List<Material> materiallist = materialRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
            for(Material material : materiallist){
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
    public Material findById(String id) {
        return materialRepository.findById(id).get();
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public List<Material> findByParam(Material material) {
        Specification<Material> spec = paramOperate(material, null);
        return materialRepository.findAll(spec);
    }

    @Override
    public Page<Material> findAll(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    @Override
    public Page<Material> findByParam(Material material, String time, Pageable pageable) {
        Specification<Material> spec = paramOperate(material, time);
        return materialRepository.findAll(spec,pageable);
    }

    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            materialRepository.deleteById(id);
        }
    }

    @Override
    public long count(Material material, String time) {
        Specification<Material> spec = paramOperate(material,time);
        return materialRepository.count(spec);
    }

    private Specification<Material> paramOperate(Material material, String time) {
        Specification<Material> spec = new Specification<Material>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(material != null){
                    if(material.getStatus() != null && !"".equals(material.getStatus())){
                        Predicate statusPredicate = cb.equal(root.get("status"), material.getStatus());
                        predicatesList.add(statusPredicate);
                    }
                    if(material.getType() != null && !"".equals(material.getType())){
                        Predicate typePredicate = cb.equal(root.get("type"), material.getType());
                        predicatesList.add(typePredicate);
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
