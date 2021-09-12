package com.guifaleiros.productservice.infrastructure.outbound.persistence;

import com.guifaleiros.productservice.infrastructure.outbound.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, String> {
}
