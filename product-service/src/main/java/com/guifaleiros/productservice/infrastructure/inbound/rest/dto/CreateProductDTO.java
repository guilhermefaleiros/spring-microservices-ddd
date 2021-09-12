package com.guifaleiros.productservice.infrastructure.inbound.rest.dto;

import com.guifaleiros.productservice.application.domain.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductDTO {
    private String name;
    private Double price;
    private Integer quantity;

    public Product toDomainModel() {
        return new Product(this.name, this.price, this.quantity);
    }
}
