package com.guifaleiros.orderservice.application.ports;

import com.guifaleiros.orderservice.application.domain.Order;

public interface OrderRepositoryPort {
    Order save(Order order);
    Order findById(String id);
}
