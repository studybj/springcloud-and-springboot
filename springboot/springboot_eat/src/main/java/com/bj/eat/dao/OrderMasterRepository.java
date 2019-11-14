package com.bj.eat.dao;

import com.bj.eat.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenId(String buyerOpenId, Pageable pageable);
}
