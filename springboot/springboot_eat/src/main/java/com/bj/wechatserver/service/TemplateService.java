package com.bj.wechatserver.service;

import com.bj.wechatserver.entity.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TemplateService {
    void save(Template template);
    void save(List<Template> lists);

    void delete(String[] ids);

    Map update(String[] ids, int updateType);

    Template findById(String id);
    List<Template> findAll();

    List<Template> findByParam(Template template);

    Page<Template> findAll(Pageable pageable);
    Page<Template> findByParam(Template template, String time, Pageable pageable);

    long count(Template template, String time);
}
