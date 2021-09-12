package com.guifaleiros.core.events.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReserveProductPayload {
    private String orderId;
    private List<OrderReserveProductItem> items;
}
