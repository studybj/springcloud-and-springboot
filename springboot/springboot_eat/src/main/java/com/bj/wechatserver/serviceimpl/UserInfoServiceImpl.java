package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.UserInfoRepository;
import com.bj.wechatserver.entity.KeyReply;
import com.bj.wechatserver.entity.UserInfo;
import com.bj.wechatserver.enums.UpdateEnums;
import com.bj.wechatserver.service.UserInfoService;
import com.bj.wechatserver.service.UserTagXService;
import com.bj.wechatserver.util.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userRepository;
    @Autowired
    private UserTagXService tagService;

    @Override
    public void save(UserInfo userInfo) {
        userRepository.save(userInfo);
    }
    @Override
    public Map update(UserInfo userInfo, int updateType) {
        UserInfo user = findByOpenId(userInfo.getOpenid());
        if(updateType == UpdateEnums.REMARK.getCode()){//更新备注
            user.setRemark(userInfo.getRemark());
            save(user);
            return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
        }else if(updateType == UpdateEnums.SUBSCRIBE_STATUS.getCode()){//更新订阅状态
            user.setSubscribe(userInfo.getSubscribe());
            save(user);
            //return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
        }else if(updateType == UpdateEnums.BLACK.getCode()){//更新黑名单
            user.setIsblack(userInfo.getIsblack());
            save(user);
            //return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
        }else if(updateType == UpdateEnums.GROUP.getCode()){//更新分组信息
            user.setGroupid(userInfo.getGroupid());
            save(user);
            //return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
        }else if(updateType == UpdateEnums.TAG.getCode()){//更新标签信息
            user.setTagid_list(userInfo.getTagid_list());
            save(user);
            //return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
        }
        return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
    }

    @Override
    public Map batchUpdate(List<UserInfo> userlist, int updateType) {
        List<UserInfo> infoList = new ArrayList<>();
        for(UserInfo userparm :userlist){
            UserInfo user = findByOpenId(userparm.getOpenid());
            if(updateType == UpdateEnums.BLACK.getCode()){//更新黑名单
                if(user.getIsblack() == 0){
                    user.setIsblack(1);
                }else{
                    user.setIsblack(0);
                }
            }else if(updateType == UpdateEnums.GROUP.getCode()){//更新分组信息
            }else if(updateType == UpdateEnums.TAG.getCode()){//更新标签信息
                /*user.setTagid_list(userInfo.getTagid_list());
                save(user);*/
                //return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
            }
            infoList.add(user);
        }
        userRepository.saveAll(infoList);
        Map map = new HashMap();
        map.put("errcode","0");
        map.put("errmsg","成功");
        return map;
        //return WeixinUtil.updateremark(user.getOpenid(),user.getRemark());
    }
    @Override
    public UserInfo findByOpenId(String openid) {
        return userRepository.findByOpenid(openid);
    }

    @Override
    public List<UserInfo> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserInfo> findByParam(UserInfo userInfo) {
        return null;
    }

    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<UserInfo> findByParam(UserInfo userInfo, String time, Pageable pageable) {
        Specification<UserInfo> spec = paramOperate(userInfo, time);
        return userRepository.findAll(spec,pageable);
    }

    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            userRepository.deleteById(id);
        }
    }

    @Override
    public long count(UserInfo userInfo, String time) {
        Specification<UserInfo> spec = paramOperate(userInfo,time);
        return userRepository.count(spec);
    }
    private Specification<UserInfo> paramOperate(UserInfo userInfo, String time) {
        Specification<UserInfo> spec = new Specification<UserInfo>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(userInfo != null){
                    if(userInfo.getOpenid() != null && !"".equals(userInfo.getOpenid())){
                        Predicate openidPredicate = cb.like(root.get("openid"), "%" + userInfo.getOpenid() + "%");
                        predicatesList.add(openidPredicate);
                    }
                    if(userInfo.getNickname() != null && !"".equals(userInfo.getNickname())){
                        Predicate nicknamePredicate = cb.like(root.get("nickname"), "%" + userInfo.getNickname() + "%");
                        predicatesList.add(nicknamePredicate);
                    }
                    if(userInfo.getSex() != null && !"".equals(userInfo.getSex())){
                        Predicate sexPredicate = cb.equal(root.get("sex"), userInfo.getSex());
                        predicatesList.add(sexPredicate);
                    }
                    if(userInfo.getCountry() != null && !"".equals(userInfo.getCountry())){
                        Predicate countryPredicate = cb.equal(root.get("country"), userInfo.getCountry());
                        predicatesList.add(countryPredicate);
                    }
                    if(userInfo.getProvince() != null && !"".equals(userInfo.getProvince())){
                        Predicate provincePredicate = cb.equal(root.get("province"), userInfo.getProvince());
                        predicatesList.add(provincePredicate);
                    }
                    if(userInfo.getCity() != null && !"".equals(userInfo.getCity())){
                        Predicate cityPredicate = cb.equal(root.get("city"), userInfo.getCity());
                        predicatesList.add(cityPredicate);
                    }
                    if(userInfo.getSubscribe_scene() != null && !"".equals(userInfo.getSubscribe_scene())){
                        Expression<Integer> exp = root.get("subscribe_scene");
                        predicatesList.add(exp.in(userInfo.getSubscribe_scene())); // 往in中添加所有id 实现in 查询
                    }
                    if(userInfo.getTagid_list() != null && !"".equals(userInfo.getTagid_list())){//TODO
                        //Predicate nicknamePredicate = cb.like(root.get("nickname"), "%" + userInfo.getNickname() + "%");
                        //predicatesList.add(nicknamePredicate);
                        List<String> useridlist = tagService.findByParam(userInfo.getTagid_list());
                        if(useridlist != null && useridlist.size() != 0) {
                            Expression<String> exp = root.get("id");
                            predicatesList.add(exp.in(useridlist)); // 往in中添加所有id 实现in 查询
                        }
                    }
                    if(time != null && !"".equals(time)){
                        String[] times = time.split("到");
                        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try{
                            Predicate timestartPredicate = cb.greaterThanOrEqualTo   (root.get("subscribe_time").as(Date.class), simple.parse(times[0]));
                            Predicate timeendPredicate = cb.lessThanOrEqualTo(root.get("subscribe_time").as(Date.class), simple.parse(times[1]));
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
