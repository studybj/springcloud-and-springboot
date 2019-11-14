package com.justtide.tms.service;

import com.justtide.tms.entity.Version;
import com.justtide.tms.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VersionService {
    @Autowired
    private VersionRepository versionRepository;


    //-----------------------------------基本操作--------------------------------------------------
    public List<Version> findAll(){
        return versionRepository.findAll();
    }
    public Version findById(Integer id){
        return versionRepository.findById(id).get();
    }
    public void save(Version version){
        versionRepository.save(version);
    }
    public void update(Version version){
        versionRepository.save(version);
    }
    public void deleteAll(){
        versionRepository.deleteAll();
    }
    public void delete(Integer id){
        versionRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Version> search(Version version){
        return versionRepository.findAll(paramOperate(version));
    }

    public Page<Version> searchPage(Version version, Pageable pageable) {
        return versionRepository.findAll(paramOperate(version), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Version> paramOperate(Version version) {
        Specification<Version> spec = new Specification<Version>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Version> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(version != null){
                    if(version.getVersionName() != null && !"".equals(version.getVersionName())){
                        Predicate versionSnnamePredicate = cb.like(root.get("versionName"), "%" + version.getVersionName() + "%");
                        predicatesList.add(versionSnnamePredicate);
                    }
                }
                if (predicatesList.size() != 0) {
                    Predicate[] p = new Predicate[predicatesList.size()];
                    return cb.and(predicatesList.toArray(p));
                }
                return null;
            }
        };
        return spec;
    }
}
