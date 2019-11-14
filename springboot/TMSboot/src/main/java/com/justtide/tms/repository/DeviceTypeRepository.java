package com.justtide.tms.repository;

import com.justtide.tms.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, String>, JpaSpecificationExecutor<DeviceType> {
}
