package com.bj.pro.search.repository;

import com.bj.pro.search.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
