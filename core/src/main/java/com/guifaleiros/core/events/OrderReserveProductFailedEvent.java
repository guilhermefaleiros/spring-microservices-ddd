package com.guifaleiros.core.events;

import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.product.OrderReserveProductFailedPayload;

public class OrderReserveProductFailedEvent extends Event<OrderReserveProductFailedPayload> {
    public OrderReserveProductFailedEvent(OrderReserveProductFailedPayload payload) {
        super("OrderReserveProductFailedEvent", payload);
    }
}
