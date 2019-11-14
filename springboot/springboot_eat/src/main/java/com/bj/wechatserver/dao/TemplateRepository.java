package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.Kefu;
import com.bj.wechatserver.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TemplateRepository extends JpaRepository<Template,String> , JpaSpecificationExecutor<Template> {
}
