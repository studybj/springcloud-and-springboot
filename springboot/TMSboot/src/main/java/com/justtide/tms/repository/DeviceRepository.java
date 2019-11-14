package com.justtide.tms.repository;

import com.justtide.tms.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceRepository extends JpaRepository<Device, Integer>, JpaSpecificationExecutor<Device> {
}
