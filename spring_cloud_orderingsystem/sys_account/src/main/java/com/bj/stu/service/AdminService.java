package com.bj.stu.service;


import com.bj.stu.entity.Admin;
import com.bj.stu.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<Admin> findAll(){
        return adminRepository.findAll();
    }
    public Admin findById(long id){
        Admin admin = (Admin) redisTemplate.opsForValue().get("admin_" + id);
        if (admin == null){
            admin = adminRepository.findById(id);
            redisTemplate.opsForValue().set("admin_" + id, admin);
        }
        return admin;
    }
    public int count(){
        return adminRepository.count();
    }
    public void save(Admin admin){
        adminRepository.save(admin);
    }
    public void update(Admin admin){
        redisTemplate.delete("admin_" + admin.getId());
        adminRepository.update(admin);
    }
    public void deleteAll(){
        redisTemplate.delete("admin_*");
        adminRepository.deleteAll();
    }
    public void delete(long id){
        redisTemplate.delete("admin_" + id);
        adminRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Admin> search(Admin admin){
        return null;
    }

    public Page<Admin> searchPage(Admin admin, Pageable pageable) {
        return null;
    }
}
