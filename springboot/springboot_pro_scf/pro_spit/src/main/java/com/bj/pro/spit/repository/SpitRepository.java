package com.bj.pro.spit.repository;

import com.bj.pro.spit.entity.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitRepository extends MongoRepository<Spit, String> {

    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
