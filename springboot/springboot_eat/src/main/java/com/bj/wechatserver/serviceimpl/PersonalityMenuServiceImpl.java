package com.bj.wechatserver.serviceimpl;

import com.bj.wechatserver.dao.PersonalityMenuRepository;
import com.bj.wechatserver.dao.WXMenuRepository;
import com.bj.wechatserver.entity.menu.*;
import com.bj.wechatserver.enums.MenuTypeEnum;
import com.bj.wechatserver.service.PersonalityMenuService;
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
public class PersonalityMenuServiceImpl implements PersonalityMenuService {
    @Autowired
    private PersonalityMenuRepository menuRepository;
    @Autowired
    private WXMenuService menuService;
    @Transactional
    @Override
    public void save(PersonalityMenu menu) throws Exception {
        System.out.println("menu = [" + menu + "]");
        menu.setCreate_time(new Date());
        String menuid = null;
        if(menu.getStatus() == 1){//TODO
            //menuid = MenuUtil.createMatchruleMenu(getMenu(), WeixinUtil.getAccessToken());
        }
        if(menuid != null){
            menu.setMenuid(menuid);
        }
        menuRepository.save(menu);
        String[] menuids = menu.getMenuidlist().split(",");
        //menuService.update(menuids,6);
    }

    @Override
    public void delete(Integer[] ids) {

    }

    @Override
    public List<PersonalityMenu> findAll() {
        return menuRepository.findAll();
    }


}
