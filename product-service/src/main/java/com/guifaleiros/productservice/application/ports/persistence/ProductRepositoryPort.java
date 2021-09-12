package com.guifaleiros.productservice.application.ports.persistence;

import com.guifaleiros.productservice.application.domain.Product;

public interface ProductRepositoryPort {
    Product save(Product product);
    Product findById(String productId);
}
