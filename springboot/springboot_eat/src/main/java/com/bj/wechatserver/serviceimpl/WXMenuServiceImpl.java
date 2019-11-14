package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.WXMenuRepository;
import com.bj.wechatserver.entity.menu.*;
import com.bj.wechatserver.enums.MenuTypeEnum;
import com.bj.wechatserver.service.WXMenuService;
import com.bj.wechatserver.util.EnumUtil;
import com.bj.wechatserver.util.WeixinUtil;
import com.bj.wechatserver.util.menu.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class WXMenuServiceImpl implements WXMenuService {
    @Autowired
    private WXMenuRepository menuRepository;
    @Transactional
    @Override
    public void save(Menu menu) throws Exception {
        if(menu.getId() == null){
            menu.setCreateTime(new Date());
        }
        if(menu.getPid() == null){
            menu.setPid(-1);
        }
        menuRepository.save(menu);
    }
    @Override
    public void save(List<Menu> lists) {
        menuRepository.saveAll(lists);
    }

    @Override
    public void delete(Integer[] ids) {
        for(Integer id : ids){
            menuRepository.deleteMenusByPid(id);
            menuRepository.deleteById(id);
        }
    }

    @Transactional
    @Override
    public Map update(Integer[] ids, int updateType) throws Exception {
        List<Menu> resList = new ArrayList<>();
        Map map = new HashMap();
        List<Menu> menulist = menuRepository.findAllById(Arrays.asList(ids));
        if(updateType == 1){//更新状态
            for(Menu menu : menulist){
                if(menu.getStatus() == 0){
                    menu.setStatus(1);
                }else{
                    menu.setStatus(0);
                }
            }
            resList.addAll(menulist);
            save(resList);
        } else if(updateType == 2){//生成普通菜单
            //先将已有普通菜单清除，在更新
            Menu menuparm = new Menu();
            menuparm.setIsordinary(1);
            List<Menu> list = findByParam(menuparm,1);
            for(Menu m : list){
                m.setIsordinary(0);
            }
            resList.addAll(list);
            for(Menu m : menulist){
                m.setStatus(1);
                m.setIsordinary(1);
            }
            resList.addAll(menulist);

            int res = MenuUtil.createMenu(getMenu(), WeixinUtil.getAccessToken());
            map.put("errcode",res);
            if(res != 0){
                map.put("errmsg","创建菜单异常");
                throw new Exception("创建菜单异常");
            }else{
                save(resList);
                map.put("errmsg","菜单创建成功");
            }
        }else if(updateType == 3){//删除普通菜单
            //TODO
        }
        if(map.get("errcode") != null){
            map.put("errcode","0");
            map.put("errmsg","更新成功");
        }
        return map;
    }

    @Override
    public Menu findById(Integer id) {
        return menuRepository.findById(id).get();
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    /**
     * @param menu
     * @param change 0为默认，即替换为类型名称，1为不替换
     * @return
     */
    @Override
    public List<Menu> findByParam(Menu menu,int change) {
        Specification<Menu> spec = paramOperate(menu);
        List<Menu> list = menuRepository.findAll(spec);
        if(change == 0){
            for(Menu m : list){
                m.setType(EnumUtil.getByCode(m.getType(),MenuTypeEnum.class).getMessage());
            }
        }
        return list;
    }
    @Override
    public List<Menu> findByMenuNameAndStatus(String menuname,Integer status) {
        return menuRepository.findByNameAndStatus(menuname,status);
    }

    @Override
    public long count(Menu menu) {
        Specification<Menu> spec = paramOperate(menu);
        return menuRepository.count(spec);
    }

    private Specification<Menu> paramOperate(Menu menu) {
        Specification<Menu> spec = new Specification<Menu>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(menu != null){
                    if(menu.getId() != null && !"".equals(menu.getId())){
                        Predicate idPredicate = cb.notEqual(root.get("id"),  menu.getId() );
                        predicatesList.add(idPredicate);
                    }
                    if(menu.getPid() != null && !"".equals(menu.getPid())){
                        if(menu.getPid() == 0) {
                            Predicate pidPredicate = cb.notEqual(root.get("pid"), -1);
                            predicatesList.add(pidPredicate);
                        }else{
                            Predicate pidPredicate = cb.equal(root.get("pid"), menu.getPid());
                            predicatesList.add(pidPredicate);
                        }
                    }
                    if(menu.getKey() != null && !"".equals(menu.getKey())){
                        Predicate keyPredicate = cb.equal(root.get("key"),  menu.getKey() );
                        predicatesList.add(keyPredicate);
                    }
                    if(menu.getName() != null && !"".equals(menu.getName())){
                        Predicate namePredicate = cb.equal(root.get("name"),  menu.getName() );
                        predicatesList.add(namePredicate);
                    }
                    if(menu.getStatus() != null && !"".equals(menu.getStatus())){
                        Predicate statusPredicate = cb.equal(root.get("status"), menu.getStatus());
                        predicatesList.add(statusPredicate);
                    }
                    if(menu.getIsordinary() != null && !"".equals(menu.getIsordinary())){
                        Predicate isordinaryPredicate = cb.equal(root.get("isordinary"), menu.getIsordinary());
                        predicatesList.add(isordinaryPredicate);
                    }
                    if(menu.getIspersonality() != null && !"".equals(menu.getIspersonality())){
                        Predicate ispersonalityPredicate = cb.equal(root.get("ispersonality"), menu.getIspersonality());
                        predicatesList.add(ispersonalityPredicate);
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
    public void save1(Menu menu) throws Exception {
        menu.setCreateTime(new Date());
        if(menu.getPid() == null){
            menu.setPid(-1);
        }
        Integer oldstatus = menuRepository.findById(menu.getId()).get().getStatus();
        menuRepository.save(menu);
        Boolean flag = false;
        if(menu.getId() == null || "".equals(menu.getId())){//新增
            if(menu.getStatus() == 1){
                //调用创建菜单
                flag = true;
            }
        }else {//更新
            flag = oldstatus == menu.getStatus() ? false : true;
        }
        System.out.println("menu = [" + oldstatus + ",menu.getStatus() :" + menu.getStatus()  + "]");
        if(flag){
            int res = MenuUtil.createMenu(getMenu(), WeixinUtil.getAccessToken());
            if(res != 0){
                throw new Exception("创建菜单异常");
            }
        }
    }
    /**
     * 组装菜单数据
     *
     * @return
     */
    private MenuInfo getMenu() {
        MenuInfo menuInfo = new MenuInfo();
        //获取一级菜单
        Menu menuParam = new Menu();
        menuParam.setPid(-1);
        menuParam.setStatus(1);
        List<Menu> menu1list = findByParam(menuParam,1);
        if(menu1list.size() <= 0){
            return null;
        }

        int i=0;
        int j=0;
        ComplexButton mainBtn1;
        CommonButton[] cb1;
        Button[] b1;
        CommonButton btn1;
        b1 = new Button[menu1list.size()];

        for(Menu menu1 : menu1list){
            menuParam = new Menu();
            menuParam.setStatus(1);
            menuParam.setPid(menu1.getId());
            List<Menu> menu2List = findByParam(menuParam,1);

            if(menu2List != null && menu2List.size() > 0){
                cb1 = new CommonButton[menu2List.size()];
                for(Menu menu2 : menu2List){
                    cb1[j] = jundgeType(menu2);
                    j++;
                }
                mainBtn1 = new ComplexButton();
                mainBtn1.setName(menu1.getName());
                mainBtn1.setSub_button(cb1);
                b1[i] = mainBtn1;
                j = 0;
            }else {
                btn1 = jundgeType(menu1);
                b1[i] = btn1;
            }
            i++;
        }
        menuInfo.setButton(b1);
        return menuInfo;
    }
    private CommonButton jundgeType(Menu menu){
        CommonButton button = null;
        if(menu.getType().equals(MenuTypeEnum.VIEW.getCode())){
            ViewButton btn = new ViewButton();
            btn.setType(menu.getType());
            btn.setName(menu.getName());
            btn.setUrl(menu.getUrl());
            button = btn;
        }else if(menu.getType().equals(MenuTypeEnum.MINIPROGRAM.getCode())){
            MiniProgramButton btn = new MiniProgramButton();
            btn.setType(menu.getType());
            btn.setName(menu.getName());
            btn.setAppid(menu.getAppid());
            btn.setPagepath(menu.getPagepath());
            button = btn;
        }else if(menu.getType().equals(MenuTypeEnum.VIEW_LIMITED.getCode())){
            LimitViewButton btn = new LimitViewButton();
            btn.setType(menu.getType());
            btn.setName(menu.getName());
            btn.setMedia_id(menu.getMedia_id());
            button = btn;
        }else {
            //处理子按钮
            CommonButton btn = new CommonButton();
            btn.setKey(menu.getKey());
            btn.setType(menu.getType());
            btn.setName(menu.getName());
            button = btn;
        }
    return button;
    }
}
