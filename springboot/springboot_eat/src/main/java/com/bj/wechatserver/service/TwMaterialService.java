package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.LsMaterial;
import com.bj.wechatserver.entity.TwMaterial;
import com.bj.wechatserver.entity.TwMaterialBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TwMaterialService {
    void save(TwMaterial material);
    void save(List<TwMaterial> lists);
    Map update(Integer[] ids, int updateType);
    TwMaterial findById(Integer id);

    List<TwMaterial> findAll();
    List<TwMaterial> findByParam(TwMaterial material);
    List<TwMaterial> findByTagName(String tagname);

    Page<TwMaterial> findAll(Pageable pageable);
    Page<TwMaterial> findByParam(TwMaterial material, String time, Pageable pageable);

    void delete(String[] ids);

    long count(TwMaterial material, String time);

    void save_base(TwMaterialBase material);
    Map update(String ids, int updateType);
    TwMaterialBase findById_base(String id);

    List<TwMaterialBase> findAll_base();
    List<TwMaterialBase> findByParam_base(TwMaterialBase material);

    Page<TwMaterialBase> findAll_base(Pageable pageable);
    Page<TwMaterialBase> findByParam_base(TwMaterialBase material, String time, Pageable pageable);

    void delete_base(String[] ids);
    long count_base(TwMaterialBase material, String time);
}
