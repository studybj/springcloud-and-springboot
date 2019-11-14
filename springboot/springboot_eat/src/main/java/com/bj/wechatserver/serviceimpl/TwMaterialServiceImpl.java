package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.TwBaseMaterialRepository;
import com.bj.wechatserver.dao.TwMaterialRepository;
import com.bj.wechatserver.entity.TwMaterial;
import com.bj.wechatserver.entity.TwMaterialBase;
import com.bj.wechatserver.service.TwMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TwMaterialServiceImpl implements TwMaterialService {
    @Autowired
    private TwMaterialRepository twmaterialRepository;
    @Autowired
    private TwBaseMaterialRepository twBasematerialRepository;
    @Override
    public void save(TwMaterial material) {
        TwMaterial ls = findById(material.getId());
        if(!material.getStatus().equals(ls.getStatus())){
            ls.setStatus(material.getStatus());
        }
        twmaterialRepository.save(ls);
    }
    @Override
    public void save(List<TwMaterial> lists) {
        twmaterialRepository.saveAll(lists);
    }
    @Transactional
    @Override
    public Map update(Integer[] ids, int updateType) {
        List<TwMaterial> materiallist = twmaterialRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
            for(TwMaterial material : materiallist){
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
    public TwMaterial findById(Integer id) {
        return twmaterialRepository.findById(id).get();
    }

    @Override
    public List<TwMaterial> findAll() {
        return twmaterialRepository.findAll();
    }

    @Override
    public List<TwMaterial> findByParam(TwMaterial material) {
        Specification<TwMaterial> spec = paramOperate(material, null);
        return twmaterialRepository.findAll(spec);
    }

    @Override
    public List<TwMaterial> findByTagName(String tagname) {
        return null;
    }

    @Override
    public Page<TwMaterial> findAll(Pageable pageable) {
        return twmaterialRepository.findAll(pageable);
    }

    @Override
    public Page<TwMaterial> findByParam(TwMaterial material, String time, Pageable pageable) {
        Specification<TwMaterial> spec = paramOperate(material, time);
        return twmaterialRepository.findAll(spec,pageable);
    }

    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            twmaterialRepository.deleteById(Integer.parseInt(id));
        }
    }

    @Override
    public long count(TwMaterial material, String time) {
        Specification<TwMaterial> spec = paramOperate(material,time);
        return twmaterialRepository.count(spec);
    }

    @Override
    public void save_base(TwMaterialBase material) {
        if(StringUtils.isEmpty(material.getId())){
            String id = UUID.randomUUID().toString().substring(0,5) + new Date().getTime();
            material.setId(id);
            twBasematerialRepository.save(material);
        }else{
            TwMaterialBase ls = findById_base(material.getId());
            twBasematerialRepository.save(ls);
        }
    }

    @Override
    public Map update(String ids, int updateType) {
        TwMaterialBase material = findById_base(ids);
        Map map = new HashMap();
        map.put("errcode","0");
        map.put("errmsg","成功");

        if(updateType == 1){//更新状态
            //TODO 更改状态，若状态为0，则更改状态并生成图文素材，若状态为1，则需查找包含该信息的素材并删除，才可更改状态

        } else {
            if(material.getStatus() == 0){
                if(updateType == 2){//2 更新显示封面
                    material.setShow_cover_pic(material.getShow_cover_pic() == 1 ? 0 : 1);
                }else if(updateType == 3){//3 更新打开评论
                    material.setNeed_open_comment(material.getNeed_open_comment() == 1 ? 0 : 1);
                }else if(updateType == 4){//4只可粉丝评论
                    material.setOnly_fans_can_comment(material.getOnly_fans_can_comment() == 1 ? 0 : 1);
                }
                save_base(material);
            }else{
                map.put("errcode","-1");
                map.put("errmsg","不可以更改信息，操作失败！");
            }
        }
        return map;
    }

    @Override
    public TwMaterialBase findById_base(String id) {
        return twBasematerialRepository.findById(id).get();
    }

    @Override
    public List<TwMaterialBase> findAll_base() {
        return twBasematerialRepository.findAll();
    }

    @Override
    public List<TwMaterialBase> findByParam_base(TwMaterialBase material) {
        Specification<TwMaterialBase> spec = paramOperate_base(material, null);
        return twBasematerialRepository.findAll(spec);
    }

    @Override
    public Page<TwMaterialBase> findAll_base(Pageable pageable) {
        return twBasematerialRepository.findAll(pageable);
    }

    @Override
    public Page<TwMaterialBase> findByParam_base(TwMaterialBase material, String time, Pageable pageable) {
        Specification<TwMaterialBase> spec = paramOperate_base(material, null);
        return twBasematerialRepository.findAll(spec,pageable);
    }

    @Override
    public void delete_base(String[] ids) {
        for(String id : ids){
            twBasematerialRepository.deleteById(id);
        }
    }

    @Override
    public long count_base(TwMaterialBase material, String time) {
        Specification<TwMaterialBase> spec = paramOperate_base(material,time);
        return twBasematerialRepository.count(spec);
    }

    private Specification<TwMaterial> paramOperate(TwMaterial material, String time) {
        Specification<TwMaterial> spec = new Specification<TwMaterial>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<TwMaterial> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(time != null && !"".equals(time)) {
                    String[] times = time.split("到");
                    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Predicate timestartPredicate = cb.greaterThanOrEqualTo(root.get("created_at").as(Date.class), simple.parse(times[0]));
                        Predicate timeendPredicate = cb.lessThanOrEqualTo(root.get("created_at").as(Date.class), simple.parse(times[1]));
                        predicatesList.add(timestartPredicate);
                        predicatesList.add(timeendPredicate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if(material != null){
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

    private Specification<TwMaterialBase> paramOperate_base(TwMaterialBase material, String time) {
        Specification<TwMaterialBase> spec = new Specification<TwMaterialBase>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<TwMaterialBase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(time != null && !"".equals(time)){
                    String[] times = time.split("到");
                    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        Predicate timestartPredicate = cb.greaterThanOrEqualTo(root.get("created_at").as(Date.class), simple.parse(times[0]));
                        Predicate timeendPredicate = cb.lessThanOrEqualTo(root.get("created_at").as(Date.class), simple.parse(times[1]));
                        predicatesList.add(timestartPredicate);
                        predicatesList.add(timeendPredicate);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                if(material != null){
                    if(material.getTitle() != null && !"".equals(material.getTitle())){
                        Predicate titlePredicate = cb.like(root.get("title"), "%" + material.getTitle() + "%");
                        predicatesList.add(titlePredicate);
                    }
                    if(material.getAuthor() != null && !"".equals(material.getAuthor())){
                        Predicate authorPredicate = cb.like(root.get("author"), "%" + material.getAuthor() + "%");
                        predicatesList.add(authorPredicate);
                    }
                    if(material.getShow_cover_pic() != null && !"".equals(material.getShow_cover_pic())){
                        Predicate show_coverPredicate = cb.equal(root.get("show_cover_pic"), material.getShow_cover_pic());
                        predicatesList.add(show_coverPredicate);
                    }
                    if(material.getNeed_open_comment() != null && !"".equals(material.getNeed_open_comment())){
                        Predicate need_open_commentPredicate = cb.equal(root.get("need_open_comment"), material.getNeed_open_comment());
                        predicatesList.add(need_open_commentPredicate);
                    }
                    if(material.getOnly_fans_can_comment() != null && !"".equals(material.getOnly_fans_can_comment())){
                        Predicate only_fansPredicate = cb.equal(root.get("only_fans_can_comment"), material.getOnly_fans_can_comment());
                        predicatesList.add(only_fansPredicate);
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
