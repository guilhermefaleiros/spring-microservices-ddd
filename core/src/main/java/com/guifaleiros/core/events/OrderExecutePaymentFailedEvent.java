package com.guifaleiros.core.events;

import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.payment.OrderExecutePaymentFailedPayload;

public class OrderExecutePaymentFailedEvent extends Event<OrderExecutePaymentFailedPayload> {
    public OrderExecutePaymentFailedEvent(OrderExecutePaymentFailedPayload payload) {
        super("OrderExecutePaymentFailedEvent", payload);
    }
}
