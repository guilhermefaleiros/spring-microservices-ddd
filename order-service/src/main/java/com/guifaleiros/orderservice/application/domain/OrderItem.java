package com.guifaleiros.orderservice.application.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItem {
    private String id;
    private String productName;
    private Integer productQuantity;
    private String productId;

    public OrderItem(String productName, Integer productQuantity, String productId) {
        this.id = UUID.randomUUID().toString();
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productId = productId;
    }

    public OrderItem(String id, String productName, Integer productQuantity, String productId) {
        this.id = id;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productId = productId;
    }
}
