package com.guifaleiros.core.events.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderReserveProductCompensationPayload {
    private String orderId;
    private List<OrderReserveProductItem> items;
}
