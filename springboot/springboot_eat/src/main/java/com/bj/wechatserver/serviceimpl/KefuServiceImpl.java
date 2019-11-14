package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.KefuRepository;
import com.bj.wechatserver.entity.Kefu;
import com.bj.wechatserver.service.KefuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class KefuServiceImpl implements KefuService {
    @Autowired
    private KefuRepository kefuRepository;

    @Override
    public void save(Kefu kefu) {
        if(StringUtils.isEmpty(kefu.getId())){
            if(kefu.getStatus() == 1){
                //TODO 走微信
                //1.添加客服

                //2.查询所有客服并找到当前客服信息，更新客服内容

            }
            if(kefu.getKf_headimgurl() != null){
                //TODO 走微信
            }
            String id = UUID.randomUUID().toString().substring(0,5) + new Date().getTime();
            kefu.setId(id);
            kefuRepository.save(kefu);
        }else{//TODO
            //if(findById(kefu.getId()).getStatus() == kefu.getStatus())
        }


    }

    @Override
    public void save(List<Kefu> lists) {
        kefuRepository.saveAll(lists);
    }

    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            kefuRepository.deleteById(id);
        }
    }

    @Override
    public Map update(String[] ids, int updateType) {
        List<Kefu> list = kefuRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
            for(Kefu tag : list){
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
    public Kefu findById(String id) {
        return kefuRepository.findById(id).get();
    }

    @Override
    public List<Kefu> findAll() {
        return kefuRepository.findAll();
    }

    @Override
    public List<Kefu> findByNickName(String name) {
        return kefuRepository.findByNickname(name);
    }

    @Override
    public List<Kefu> findByParam(Kefu kefu) {
        Specification<Kefu> spec = paramOperate(kefu, null);
        return kefuRepository.findAll(spec);
    }

    @Override
    public Page<Kefu> findAll(Pageable pageable) {
        return kefuRepository.findAll(pageable);
    }

    @Override
    public Page<Kefu> findByParam(Kefu kefu, String time, Pageable pageable) {
        Specification<Kefu> spec = paramOperate(kefu, time);
        return kefuRepository.findAll(spec,pageable);
    }

    @Override
    public long count(Kefu kefu, String time) {
        Specification<Kefu> spec = paramOperate(kefu,time);
        return kefuRepository.count(spec);
    }

    private Specification<Kefu> paramOperate(Kefu kefu, String time) {
        Specification<Kefu> spec = new Specification<Kefu>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Kefu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(kefu != null){
                if (kefu.getNickname() != null && !"".equals(kefu.getNickname())) {
                    Predicate namePredicate = cb.like(root.get("nickname"), "%" + kefu.getNickname() + "%");
                    predicatesList.add(namePredicate);
                }
                if (kefu.getKf_id() != null && !"".equals(kefu.getKf_id())) {
                    Predicate kf_idPredicate = cb.like(root.get("kf_id"), "%" + kefu.getKf_id() + "%");
                    predicatesList.add(kf_idPredicate);
                }
                if (kefu.getKf_account() != null && !"".equals(kefu.getKf_account())) {
                    Predicate accountPredicate = cb.like(root.get("kf_account"), "%" + kefu.getKf_account() + "%");
                    predicatesList.add(accountPredicate);
                }
                if (kefu.getKf_nick() != null && !"".equals(kefu.getKf_nick())) {
                    Predicate nickPredicate = cb.like(root.get("kf_nick"), "%" + kefu.getKf_nick() + "%");
                    predicatesList.add(nickPredicate);
                }
                if (kefu.getStatus() != null && !"".equals(kefu.getStatus())) {
                    Predicate statusPredicate = cb.equal(root.get("status"), kefu.getStatus());
                    predicatesList.add(statusPredicate);
                }
                if (time != null && !"".equals(time)) {
                    String[] times = time.split("到");
                    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Predicate timestartPredicate = cb.greaterThanOrEqualTo(root.get("create_time").as(Date.class), simple.parse(times[0]));
                        Predicate timeendPredicate = cb.lessThanOrEqualTo(root.get("create_time").as(Date.class), simple.parse(times[1]));
                        predicatesList.add(timestartPredicate);
                        predicatesList.add(timeendPredicate);
                    } catch (Exception e) {
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
