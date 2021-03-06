package com.bj.eat.dao;

import com.bj.eat.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
    Page<OrderDetail> findByOrderId(String orderId, Pageable pageable);
}
