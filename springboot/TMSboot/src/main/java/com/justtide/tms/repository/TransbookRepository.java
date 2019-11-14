package com.justtide.tms.repository;

import com.justtide.tms.entity.Transbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransbookRepository extends JpaRepository<Transbook, Integer>, JpaSpecificationExecutor<Transbook> {
}
