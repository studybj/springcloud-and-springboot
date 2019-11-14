package com.bj.pro.qa.repository;

import com.bj.pro.qa.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


//JpaSpecificationExecutor 实现复杂条件查询
public interface ProblemRepository extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    /**
     * 最新回答问题列表
     * @return
     */
    @Query(value = "select * FROM tb_problem, tb_pl WHERE id = problemid AND labelid = ? ORDER BY replytime DESC ", nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);

    /**
     * 热门回答问题列表
     * @return
     * nativeQuery = true表示可以通过表名来书写sql放在value中，注意此处中间表为建立映射类
     */
    @Query(value = "select * FROM tb_problem, tb_pl WHERE id = problemid AND labelid = ? ORDER BY reply DESC ", nativeQuery = true)
    public Page<Problem> hotlist(String labelid, Pageable pageable);

    /**
     * 等待回答问题列表
     * @return
     */
    @Query(value = "select * FROM tb_problem, tb_pl WHERE id = problemid AND labelid = ? AND reply = 0 ORDER BY createtime DESC ", nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);
}
