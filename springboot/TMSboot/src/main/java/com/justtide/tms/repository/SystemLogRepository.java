package com.justtide.tms.repository;

import com.justtide.tms.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SystemLogRepository extends JpaRepository<SystemLog, Integer>, JpaSpecificationExecutor<SystemLog> {
}
