package com.guifaleiros.orderservice.infrastructure.outbound.persistence;

import com.guifaleiros.orderservice.infrastructure.outbound.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, String> {
}
