package com.justtide.tms.repository;

import com.justtide.tms.entity.WhiteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WhiteListRepository extends JpaRepository<WhiteList, Integer>, JpaSpecificationExecutor<WhiteList> {
}
