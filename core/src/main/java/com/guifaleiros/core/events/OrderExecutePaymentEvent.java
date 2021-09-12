package com.guifaleiros.core.events;

import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.payment.OrderExecutePaymentPayload;

public class OrderExecutePaymentEvent extends Event<OrderExecutePaymentPayload> {
    public OrderExecutePaymentEvent(OrderExecutePaymentPayload payload) {
        super("OrderExecutePaymentEvent", payload);
    }
}
