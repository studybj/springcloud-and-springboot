package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.LsMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface LsMaterialService {
    void save(LsMaterial material);
    void save(List<LsMaterial> lists);
    Map update (String[] ids, int updateType);
    LsMaterial findById(String id);

    List<LsMaterial> findAll();
    List<LsMaterial> findByParam(LsMaterial material);
    List<LsMaterial> findByTagName(String tagname);

    Page<LsMaterial> findAll(Pageable pageable);
    Page<LsMaterial> findByParam(LsMaterial material, String time, Pageable pageable);

    void delete(String[] ids);

    long count(LsMaterial material, String time);
}
