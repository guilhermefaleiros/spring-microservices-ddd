package com.guifaleiros.orderservice.application.services;

import com.guifaleiros.orderservice.application.domain.Order;

public interface OrderService {
    Order save(Order order);
}
