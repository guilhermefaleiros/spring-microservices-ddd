package com.guifaleiros.core.events.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderExecutePaymentPayload {
    private String orderId;
    private String userId;
    private Double price;
}
