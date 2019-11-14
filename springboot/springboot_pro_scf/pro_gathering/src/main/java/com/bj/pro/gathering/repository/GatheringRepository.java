package com.bj.pro.gathering.repository;

import com.bj.pro.gathering.entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//JpaSpecificationExecutor 实现复杂条件查询
public interface GatheringRepository extends JpaRepository<Gathering, String>, JpaSpecificationExecutor<Gathering> {
    /**
     * 更新文章状态
     * @param id
     * 当涉及增删改操作时需要添加modifying注解
     */
    @Modifying
    @Query(value = "update tb_article SET state = :state WHERE id = :id", nativeQuery = true)
    public void updateState(@Param("state") String state, @Param("id") String id);

    /**
     *  更新点赞数
     *  @param num
     * @param id
     */
    @Modifying
    @Query(value = "update tb_article SET thumbup = thumbup + :num WHERE id = :id", nativeQuery = true)
    public void updateThumbup(@Param("num") int num, @Param("id") String id);
}
