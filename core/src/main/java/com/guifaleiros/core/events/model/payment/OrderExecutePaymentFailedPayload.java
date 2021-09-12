package com.guifaleiros.core.events.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderExecutePaymentFailedPayload {
    private String orderId;
    private String message;
}
