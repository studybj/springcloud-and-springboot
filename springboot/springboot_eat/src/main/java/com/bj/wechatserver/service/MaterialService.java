package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.Material;
import com.bj.wechatserver.entity.UserTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MaterialService {
    void save(Material material);
    void save(List<Material> lists);
    Map update (String[] ids, int updateType);
    Material findById(String id);

    List<Material> findAll();
    List<Material> findByParam(Material material);

    Page<Material> findAll(Pageable pageable);
    Page<Material> findByParam(Material material, String time, Pageable pageable);

    void delete(String[] ids);

    long count(Material material, String time);
}
