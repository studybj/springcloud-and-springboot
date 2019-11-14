package com.bj.pro.qa.repository;

import com.bj.pro.qa.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


//JpaSpecificationExecutor 实现复杂条件查询
public interface ReplyRepository extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {
}
