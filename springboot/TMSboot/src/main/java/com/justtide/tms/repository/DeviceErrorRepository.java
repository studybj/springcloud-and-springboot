package com.justtide.tms.repository;

import com.justtide.tms.entity.Device;
import com.justtide.tms.entity.DeviceError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceErrorRepository extends JpaRepository<DeviceError, Integer>, JpaSpecificationExecutor<DeviceError> {
}
