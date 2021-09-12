package com.guifaleiros.core.events.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderExecutePaymentSuccessPayload {
    private String orderId;
}
