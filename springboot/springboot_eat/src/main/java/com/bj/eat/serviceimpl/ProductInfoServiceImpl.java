package com.bj.eat.serviceimpl;

import com.bj.eat.dao.ProductCategoryRepository;
import com.bj.eat.dao.ProductInfoRepository;
import com.bj.eat.dto.CartDto;
import com.bj.eat.entity.ProductCategory;
import com.bj.eat.entity.ProductInfo;
import com.bj.eat.enums.ProductStatusEnum;
import com.bj.eat.enums.ResultEnum;
import com.bj.eat.exception.SellException;
import com.bj.eat.service.ProductCategoryService;
import com.bj.eat.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }
    @Override
    public List<ProductInfo> findAll() {
        return repository.findAll();
    }
    @Override
    public List<ProductInfo> findUpAll(){
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
    @Override
    public ProductInfo update(ProductInfo productInfo) {
        ProductInfo pc = repository.findById(productInfo.getId()).get();
        pc = productInfo;
        return repository.save(pc);
    }
    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartList) {
        for (CartDto cartDto : cartList){
            ProductInfo productInfo = findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartList) {
        for (CartDto cartDto : cartList){
            ProductInfo productInfo = findOne(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
    @Override
    @Transactional
    public void productUp(String productId) {
        ProductInfo productInfo = findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ISUP);
        }else{
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void productDown(String productId) {
        ProductInfo productInfo = findOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ISDOWN);
        }else{
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
            repository.save(productInfo);
        }
    }
    @Override
    @Transactional
    public void productUpBatch(List<String> productList) {
        for(String productId : productList){
            productUp(productId);
        }
    }
    @Override
    @Transactional
    public void productDownBatch(List<String> productList) {
        for(String productId : productList){
            productDown(productId);
        }
    }
}
