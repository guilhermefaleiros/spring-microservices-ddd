package com.guifaleiros.core.events.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderReserveProductItem {
    private String productId;
    private Integer quantity;
}
