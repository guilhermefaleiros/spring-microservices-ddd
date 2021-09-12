package com.guifaleiros.productservice.infrastructure.outbound.persistence;

import com.guifaleiros.productservice.application.domain.Product;
import com.guifaleiros.productservice.application.ports.persistence.ProductRepositoryPort;
import com.guifaleiros.productservice.infrastructure.outbound.persistence.entities.ProductEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MySQLProductRepository implements ProductRepositoryPort {

    private final SpringDataProductRepository springDataProductRepository;

    @Autowired
    public MySQLProductRepository(SpringDataProductRepository springDataProductRepository) {
        this.springDataProductRepository = springDataProductRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(product, productEntity);
        this.springDataProductRepository.save(productEntity);
        return product;
    }

    @Override
    public Product findById(String productId) {
        var productEntityOptional = this.springDataProductRepository.findById(productId);
        if(productEntityOptional.isEmpty()) {
            return null;
        }
        var productEntity = productEntityOptional.get();
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }

}
