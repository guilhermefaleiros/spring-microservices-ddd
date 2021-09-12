package com.guifaleiros.productservice.application.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private String id;
    private String name;
    private Double price;
    private Integer quantity;

    public Product(String name, Double price, Integer quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }
}
