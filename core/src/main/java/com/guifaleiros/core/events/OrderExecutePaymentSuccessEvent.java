package com.guifaleiros.core.events;

import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.payment.OrderExecutePaymentSuccessPayload;

public class OrderExecutePaymentSuccessEvent extends Event<OrderExecutePaymentSuccessPayload> {
    public OrderExecutePaymentSuccessEvent(OrderExecutePaymentSuccessPayload payload) {
        super("OrderExecutePaymentSuccessEvent", payload);
    }
}
