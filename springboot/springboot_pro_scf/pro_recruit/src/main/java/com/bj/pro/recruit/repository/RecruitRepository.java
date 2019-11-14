package com.bj.pro.recruit.repository;

import com.bj.pro.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

//JpaSpecificationExecutor 实现复杂条件查询
public interface RecruitRepository extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {
    public List<Recruit> findByState(String state);
    /**
     * 获取指定状态正序(从小到大)排序后的前6条记录
     * @param state
     * @return
     */
    public List<Recruit> findTop6ByStateOrderByCreatetime(String state);

    /**
     * 获取不是指定状态的记录并倒序排序
     * @param state
     * @return
     */
    public List<Recruit> findByStateNotOrderByCreatetimeDesc(String state);
}
