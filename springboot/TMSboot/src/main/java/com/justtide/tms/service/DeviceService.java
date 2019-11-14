package com.justtide.tms.service;

import com.justtide.tms.entity.Device;
import com.justtide.tms.entity.DeviceError;
import com.justtide.tms.entity.Transbook;
import com.justtide.tms.repository.DeviceRepository;
import com.justtide.tms.util.TempleteParseUtil;
import com.justtide.tms.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TransbookService transbookService;
    @Autowired
    private DeviceErrorService deviceErrorService;

    //-----------------------------------基本操作--------------------------------------------------
    public List<Device> findAll(){
        return deviceRepository.findAll();
    }
    public Device findById(Integer id){
        Device device = (Device) redisTemplate.opsForValue().get("device_" + id);
        if (device == null){
            device = deviceRepository.findById(id).get();
            redisTemplate.opsForValue().set("device_" + id, device);
        }
        return device;
    }
    public void save(Device device){
        deviceRepository.save(device);
    }
    public void update(Device device){
        redisTemplate.delete("device_" + device.getId());
        deviceRepository.save(device);
    }
    public void deleteAll(){
        redisTemplate.delete("device_*");
        deviceRepository.deleteAll();
    }
    public void delete(Integer id){
        redisTemplate.delete("device_" + id);
        deviceRepository.deleteById(id);
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Device> search(Device device){
        return deviceRepository.findAll(paramOperate(device));
    }

    public Page<Device> searchPage(Device device, Pageable pageable) {
        return deviceRepository.findAll(paramOperate(device), pageable);
    }


    //-----------------------------------其他操作--------------------------------------------------
    public Page<DeviceError> searchErrPage(DeviceError deviceError, PageRequest pageRequest) {
       return deviceErrorService.searchPage(deviceError, pageRequest);
    }
    public Page<Transbook> searchTransPage(Transbook transbook, PageRequest pageRequest) {
        return transbookService.searchPage(transbook, pageRequest);
    }
    public void batchSave(MultipartFile file){
        String filePath = "/Users/itinypocket/workspace/temp/";
        String path = FileUtil.upload(file, filePath);
        //开始解析
        List<Device> devices = TempleteParseUtil.devicExecute(path);
        deviceRepository.saveAll(devices);
    }

    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Device> paramOperate(Device device) {
        Specification<Device> spec = new Specification<Device>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(device != null){
                    if(device.getDeviceSn() != null && !"".equals(device.getDeviceSn())){
                        Predicate deviceSnnamePredicate = cb.like(root.get("deviceSn"), "%" + device.getDeviceSn() + "%");
                        predicatesList.add(deviceSnnamePredicate);
                    }
                    if(device.getDeviceType() != null && !"".equals(device.getDeviceType())){
                        Predicate deviceTypePredicate = cb.equal(root.get("deviceType"), device.getDeviceType());
                        predicatesList.add(deviceTypePredicate);
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
