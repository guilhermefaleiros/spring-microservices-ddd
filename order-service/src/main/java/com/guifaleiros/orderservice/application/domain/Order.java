package com.guifaleiros.orderservice.application.domain;

import com.guifaleiros.orderservice.application.domain.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Order {
    private String id;
    private Double total;
    private String userId;
    private LocalDateTime date;
    private List<OrderItem> items;
    private OrderStatus status;
    private String statusMessage;

    public Order(Double total, String userId, LocalDateTime date, List<OrderItem> items, OrderStatus status, String statusMessage) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.total = total;
        this.date = date;
        this.items = items;
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public Order(String id, Double total, String userId, LocalDateTime date, List<OrderItem> items, OrderStatus status, String statusMessage) {
        this.id = id;
        this.total = total;
        this.userId = userId;
        this.date = date;
        this.items = items;
        this.status = status;
        this.statusMessage = statusMessage;
    }
}
