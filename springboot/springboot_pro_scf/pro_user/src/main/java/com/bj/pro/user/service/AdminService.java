package com.bj.pro.user.service;

import com.bj.pro.user.entity.Admin;
import com.bj.pro.user.repository.AdminRepository;
import exception.OwnRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    //-----------------------------------基本操作--------------------------------------------------
    public List<Admin> findAll() {

        return adminRepository.findAll();
    }

    public Admin findById(String id) {
        Admin admin = (Admin) redisTemplate.opsForValue().get("admin_" + id);
        if (admin == null) {
            admin = adminRepository.findById(id).get();
            redisTemplate.opsForValue().set("admin_" + id, admin);
        }
        return admin;
    }
    public boolean isExist(String loginname){
        int count = adminRepository.countByLoginname(loginname);
        return count == 0 ? false : true;
    }
    public void save(Admin admin) {
        if(isExist(admin.getLoginname())){
            throw new OwnRuntimeException("该用户名已存在");
        }
        admin.setId(idWorker.nextId() + "");
        admin.setPassword(encoder.encode(admin.getPassword()));//加密
        adminRepository.save(admin);
    }

    public void update(Admin admin) {
        redisTemplate.delete("admin_" + admin.getId());
        adminRepository.save(admin);
    }

    public void deleteAll() {
        redisTemplate.delete("admin_*");
        adminRepository.deleteAll();
    }

    public void delete(String id) {
        redisTemplate.delete("admin_" + id);
        adminRepository.deleteById(id);
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Admin> search(Admin admin) {
        return adminRepository.findAll(paramOperate(admin));
    }

    public Page<Admin> searchPage(Admin admin, Pageable pageable) {
        return adminRepository.findAll(paramOperate(admin), pageable);
    }

    public Admin login(Admin loginAdmin) {
        if (loginAdmin == null || StringUtils.isBlank(loginAdmin.getLoginname())){
            throw  new OwnRuntimeException("请输入用户名");
        }
        Admin admin = adminRepository.findByLoginname(loginAdmin.getLoginname());
        if (admin == null){
            throw  new OwnRuntimeException("用户不存在");
        }
        if (!encoder.matches(loginAdmin.getPassword(), admin.getPassword())){
            throw  new OwnRuntimeException("密码不正确");
        }
        return admin;
    }


    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Admin> paramOperate(Admin admin) {
        Specification<Admin> spec = new Specification<Admin>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if (admin != null) {
                    if (!StringUtils.isBlank(admin.getLoginname())) {//admin.getName() != null && !"".equals(admin.getName())
                        Predicate namePredicate = cb.like(root.get("loginname"), "%" + admin.getLoginname() + "%");
                        predicatesList.add(namePredicate);
                    }
                    if (!StringUtils.isEmpty(admin.getState())) {
                        Predicate statePredicate = cb.equal(root.get("state"), admin.getState());
                        predicatesList.add(statePredicate);
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
