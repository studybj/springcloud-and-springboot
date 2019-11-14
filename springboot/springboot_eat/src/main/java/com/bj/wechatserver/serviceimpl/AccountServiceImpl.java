package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.AccountRepository;
import com.bj.wechatserver.entity.Account;
import com.bj.wechatserver.service.AccountService;
import com.bj.wechatserver.util.ImageUtil;
import com.bj.wechatserver.util.WeixinUtil_test;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void save(Account account) {
        if(StringUtils.isEmpty(account.getId())){
            if(account.getStatus() == 1){
                //TODO 走微信 getQrCodeParam
                JSONObject res = WeixinUtil_test.createQrCode(getQrCodeParam(account));
                if(res != null){
                    account.setTicket(res.getString("ticket"));
                    account.setExpire_seconds(res.getInt("expire_seconds"));
                    account.setUrl(res.getString("url"));
                    //保存该二维码到本地local_url
                    account.setLocal_url(WeixinUtil_test.getRQcode(res.getString("ticket"), "E:/weixin/account/base"));
                    //与源图片合成最终二维码
                    //account.setResult_url(ImageUtil.getQrImgUrl());
                }else{
                    try {
                        throw new Exception("生成二维码失败");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            String id = UUID.randomUUID().toString().substring(0,5) + new Date().getTime();
            account.setId(id);
            accountRepository.save(account);
        }else{//TODO 更新
        }
    }
    @Override
    public void save(List<Account> lists){
        accountRepository.saveAll(lists);
    }
    @Override
    public void delete(String[] ids) {
        for(String id : ids){
            accountRepository.deleteById(id);
        }
    }
    @Override
    public Map update(String[] ids, int updateType) {
        List<Account> list = accountRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
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
    public Account findById(String id) {
        return accountRepository.findById(id).get();
    }
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    @Override
    public List<Account> findByParam(Account account) {
        Specification<Account> spec = paramOperate(account, null);
        return accountRepository.findAll(spec);
    }
    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
    @Override
    public Page<Account> findByParam(Account account, String time, Pageable pageable) {
        Specification<Account> spec = paramOperate(account, time);
        return accountRepository.findAll(spec,pageable);
    }
    @Override
    public long count(Account account, String time) {
        Specification<Account> spec = paramOperate(account,time);
        return accountRepository.count(spec);
    }
    private Specification<Account> paramOperate(Account account, String time) {
        Specification<Account> spec = new Specification<Account>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(account != null){
                    if (account.getType() != null && !"".equals(account.getType())) {
                        Predicate typePredicate = cb.equal(root.get("type"), account.getType());
                        predicatesList.add(typePredicate);
                    }
                    if (account.getAction_name() != null && !"".equals(account.getAction_name())) {
                        Predicate ptypePredicate = cb.equal(root.get("action_name"), account.getAction_name());
                        predicatesList.add(ptypePredicate);
                    }
                    if (account.getScene_id() != null && !"".equals(account.getScene_id()) && StringUtils.isEmpty(account.getScene_str())) {
                        Predicate scene_idPredicate = cb.equal(root.get("scene_id"), account.getScene_id());
                        predicatesList.add(scene_idPredicate);
                    }
                    if (account.getScene_str() != null && !"".equals(account.getScene_str()) && StringUtils.isEmpty(account.getScene_id())) {
                        Predicate scene_strPredicate = cb.like(root.get("scene_str"), "%" + account.getScene_str() + "%");
                        predicatesList.add(scene_strPredicate);
                    }
                    if (account.getScene_id() != null && !"".equals(account.getScene_id()) && !StringUtils.isEmpty(account.getScene_str())) {
                        List<Predicate> preOrList = new ArrayList<>();
                        Predicate sceneidPredicate = cb.equal(root.get("scene_id"), account.getScene_id());
                        Predicate scenestrPredicate = cb.like(root.get("scene_str"), "%" + account.getScene_str() + "%");
                        preOrList.add(sceneidPredicate);
                        preOrList.add(scenestrPredicate);
                        Predicate[] arrayOr = new Predicate[preOrList.size()];
                        Predicate Pre_Or = cb.or(preOrList.toArray(arrayOr));
                        predicatesList.add(Pre_Or);
                    }
                    if (account.getStatus() != null && !"".equals(account.getStatus())) {
                        Predicate statusPredicate = cb.equal(root.get("status"), account.getStatus());
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

    private String getQrCodeParam(Account account){
        JSONObject jsonObject = new JSONObject();
        if(account.getType() == 1){
            jsonObject.put("expire_seconds", account.getExpire_seconds());
        }
        jsonObject.put("action_name", account.getAction_name());

        JSONObject lsJson = new JSONObject();
        if(StringUtils.isEmpty(account.getScene_id())){
            lsJson.put("scene_str", account.getScene_id());
        }else{
            lsJson.put("scene_id", account.getScene_str());
        }
        JSONObject lsJsons = new JSONObject();
        lsJsons.put("scene", lsJson.toString());
        jsonObject.put("action_info", lsJsons.toString());

        return jsonObject.toString();
    }
}
