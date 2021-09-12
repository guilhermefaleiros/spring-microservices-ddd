package com.guifaleiros.productservice.application.services.impl;

import com.guifaleiros.productservice.application.domain.Product;
import com.guifaleiros.productservice.application.ports.persistence.ProductRepositoryPort;
import com.guifaleiros.productservice.application.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryPort productRepository;

    public ProductServiceImpl(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
