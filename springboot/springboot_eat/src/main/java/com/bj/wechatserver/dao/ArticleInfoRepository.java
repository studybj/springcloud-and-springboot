package com.bj.wechatserver.dao;

import com.bj.wechatserver.entity.ArticleInfo;
import com.bj.wechatserver.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleInfoRepository extends JpaRepository<ArticleInfo,String> {
}
