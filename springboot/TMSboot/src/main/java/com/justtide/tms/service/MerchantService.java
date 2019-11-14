package com.justtide.tms.service;

import com.justtide.tms.entity.Merchant;
import com.justtide.tms.repository.MerchantRepository;
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
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;


    //-----------------------------------基本操作--------------------------------------------------
    public List<Merchant> findAll(){
        return merchantRepository.findAll();
    }
    public Merchant findById(String id){
        return merchantRepository.findById(id).get();
    }
    public void save(Merchant merchant){
        merchantRepository.save(merchant);
    }
    public void update(Merchant merchant){
        merchantRepository.save(merchant);
    }
    public void deleteAll(){
        merchantRepository.deleteAll();
    }
    public void delete(String id){
        merchantRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<Merchant> search(Merchant merchant){
        return merchantRepository.findAll(paramOperate(merchant));
    }

    public Page<Merchant> searchPage(Merchant merchant, Pageable pageable) {
        return merchantRepository.findAll(paramOperate(merchant), pageable);
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<Merchant> paramOperate(Merchant merchant) {
        Specification<Merchant> spec = new Specification<Merchant>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<Merchant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(merchant != null){
                    if(merchant.getMerchantName() != null && !"".equals(merchant.getMerchantName())){
                        Predicate merchantSnnamePredicate = cb.like(root.get("merchantName"), "%" + merchant.getMerchantName() + "%");
                        predicatesList.add(merchantSnnamePredicate);
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
