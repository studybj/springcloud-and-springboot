package com.justtide.tms.repository;

import com.justtide.tms.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VersionRepository extends JpaRepository<Version, Integer>, JpaSpecificationExecutor<Version> {
}
