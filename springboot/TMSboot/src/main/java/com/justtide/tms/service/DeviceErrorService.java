package com.justtide.tms.service;

import com.justtide.tms.entity.DeviceError;
import com.justtide.tms.repository.DeviceErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
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
public class DeviceErrorService {
    @Autowired
    private DeviceErrorRepository deviceErrorRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<DeviceError> findAll(){
        return deviceErrorRepository.findAll();
    }
    public DeviceError findById(Integer id){
        DeviceError deviceError = (DeviceError) redisTemplate.opsForValue().get("deviceError_" + id);
        if (deviceError == null){
            deviceError = deviceErrorRepository.findById(id).get();
            redisTemplate.opsForValue().set("deviceError_" + id, deviceError);
        }
        return deviceError;
    }
    public void save(DeviceError deviceError){
        deviceErrorRepository.save(deviceError);
    }
    public void update(DeviceError deviceError){
        redisTemplate.delete("deviceError_" + deviceError.getId());
        deviceErrorRepository.save(deviceError);
    }
    public void deleteAll(){
        redisTemplate.delete("deviceError_*");
        deviceErrorRepository.deleteAll();
    }
    public void delete(Integer id){
        redisTemplate.delete("deviceError_" + id);
        deviceErrorRepository.deleteById(id);
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<DeviceError> search(DeviceError deviceError){
        return deviceErrorRepository.findAll(paramOperate(deviceError));
    }

    public Page<DeviceError> searchPage(DeviceError deviceError, Pageable pageable) {
        return deviceErrorRepository.findAll(paramOperate(deviceError), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<DeviceError> paramOperate(DeviceError deviceError) {
        Specification<DeviceError> spec = new Specification<DeviceError>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<DeviceError> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(deviceError != null){
                    if(deviceError.getDeviceSn() != null && !"".equals(deviceError.getDeviceSn())){
                        Predicate deviceErrorSnnamePredicate = cb.like(root.get("deviceSn"), "%" + deviceError.getDeviceSn() + "%");
                        predicatesList.add(deviceErrorSnnamePredicate);
                    }
                    if(deviceError.getDeviceType() != null && !"".equals(deviceError.getDeviceType())){
                        Predicate deviceErrorTypePredicate = cb.equal(root.get("deviceType"), deviceError.getDeviceType());
                        predicatesList.add(deviceErrorTypePredicate);
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
