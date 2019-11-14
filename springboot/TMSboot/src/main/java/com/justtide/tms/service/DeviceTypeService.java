package com.justtide.tms.service;

import com.justtide.tms.entity.DeviceType;
import com.justtide.tms.repository.DeviceTypeRepository;
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
public class DeviceTypeService {
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    //-----------------------------------基本操作--------------------------------------------------
    public List<DeviceType> findAll(){
        return deviceTypeRepository.findAll();
    }
    public DeviceType findById(String id){
        DeviceType deviceType = (DeviceType) redisTemplate.opsForValue().get("deviceType_" + id);
        if (deviceType == null){
            deviceType = deviceTypeRepository.findById(id).get();
            redisTemplate.opsForValue().set("deviceType_" + id, deviceType);
        }
        return deviceType;
    }
    public void save(DeviceType deviceType){
        deviceTypeRepository.save(deviceType);
    }
    public void update(DeviceType deviceType){
        redisTemplate.delete("deviceType_" + deviceType.getDeviceTypeId());
        deviceTypeRepository.save(deviceType);
    }
    public void deleteAll(){
        redisTemplate.delete("deviceType_*");
        deviceTypeRepository.deleteAll();
    }
    public void deleteBatch(List<DeviceType> dts){
        List<String> dtids = new ArrayList<>();
        for (DeviceType deviceType : dts){
            dtids.add("deviceType_" + deviceType.getDeviceTypeId());
        }
        redisTemplate.delete(dtids);
        deviceTypeRepository.deleteInBatch(dts);
    }
    public void delete(String id){
        redisTemplate.delete("deviceType_" + id);
        deviceTypeRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<DeviceType> search(DeviceType deviceType){
        return deviceTypeRepository.findAll(paramOperate(deviceType));
    }

    public Page<DeviceType> searchPage(DeviceType deviceType, Pageable pageable) {
        return deviceTypeRepository.findAll(paramOperate(deviceType), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<DeviceType> paramOperate(DeviceType deviceType) {
        Specification<DeviceType> spec = new Specification<DeviceType>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<DeviceType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(deviceType != null){
                    if(deviceType.getDeviceTypeName() != null && !"".equals(deviceType.getDeviceTypeName())){
                        Predicate deviceTypeSnnamePredicate = cb.like(root.get("deviceTypeName"), "%" + deviceType.getDeviceTypeName() + "%");
                        predicatesList.add(deviceTypeSnnamePredicate);
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
