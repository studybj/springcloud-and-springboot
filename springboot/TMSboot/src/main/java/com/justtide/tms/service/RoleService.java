package com.justtide.tms.service;

import com.justtide.tms.entity.Role;
import com.justtide.tms.repository.RoleRepository;
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
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    //-----------------------------------基本操作--------------------------------------------------
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
    public Role findById(Integer id){
        return roleRepository.findById(id).get();
    }
    public void save(Role role){
        roleRepository.save(role);
    }
    public void update(Role role){
        roleRepository.save(role);
    }
    public void deleteAll(){
        roleRepository.deleteAll();
    }
    public void delete(Integer id){
        roleRepository.deleteById(id);
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Role> search(Role role){
        return roleRepository.findAll(paramOperate(role));
    }

    public Page<Role> searchPage(Role role, Pageable pageable) {
        return roleRepository.findAll(paramOperate(role), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Role> paramOperate(Role role) {
        Specification<Role> spec = new Specification<Role>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(role != null){
                    if(role.getRoleName() != null && !"".equals(role.getRoleName())){
                        Predicate rolenamePredicate = cb.like(root.get("roleName"), "%" + role.getRoleName() + "%");
                        predicatesList.add(rolenamePredicate);
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
