package com.guifaleiros.core.events.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReserveProductFailedPayload {
    private String orderId;
    private String message;
}
