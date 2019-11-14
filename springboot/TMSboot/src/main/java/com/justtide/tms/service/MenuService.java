package com.justtide.tms.service;

import com.justtide.tms.entity.Menu;
import com.justtide.tms.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;


    //-----------------------------------基本操作--------------------------------------------------
    public List<Menu> findAll(){
        return menuRepository.findAll();
    }
    public Menu findById(Integer id){
        return menuRepository.findById(id).get();
    }
    public void save(Menu menu){
        menuRepository.save(menu);
    }
    public void update(Menu menu){
        menuRepository.save(menu);
    }
    public void deleteAll(){
        menuRepository.deleteAll();
    }
    public void delete(Integer id){
        menuRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Menu> search(Menu menu){
        return menuRepository.findAll(paramOperate(menu));
    }

    public Page<Menu> searchPage(Menu menu, Pageable pageable) {
        return menuRepository.findAll(paramOperate(menu), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Menu> paramOperate(Menu menu) {
        Specification<Menu> spec = new Specification<Menu>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(menu != null){
                    if(menu.getName() != null && !"".equals(menu.getName())){
                        Predicate menuSnnamePredicate = cb.like(root.get("name"), "%" + menu.getName() + "%");
                        predicatesList.add(menuSnnamePredicate);
                    }
                }
                if (predicatesList.size() != 0) {
                    Predicate[] p = new Predicate[predicatesList.size()];
                    return cb.and(predicatesList.toArray(p));
                }
                return null;
            }
        };
        return spec;
    }
}
