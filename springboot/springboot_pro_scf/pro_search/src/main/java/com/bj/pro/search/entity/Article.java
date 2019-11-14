package com.bj.pro.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章搜索 连接elasticsearch
 * 无需所有字段，搜索需要展示的字段即可
 *
 */
@Data
@Document(indexName = "pro_db", type = "article")
public class Article implements Serializable {
    @Id
    private String id;
    /** 文章标题 */
    /**
     * 该注解对应数据库的列
     *  index           是否索引，表示该字段(域)是否能被搜索，是否分词，表示搜索的时候是整体匹配还是单词匹配，是否存储，表示是否在页面上显示，即文档(该类)中的字段
     *  analyzer        表示存储的时候，按指定的格式分词
     *  searchAnalyzer  表示查询的时候，按指定的格式分词
     *  analyzer和searchAnalyzer一致才能搜索
     */
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;
    /** 文章内容 */
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;
    /** 审核状态    0：未审核，1：已审核 */
    private String  state;
}
