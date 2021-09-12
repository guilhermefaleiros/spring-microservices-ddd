package com.guifaleiros.core.events;

import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.product.OrderReserveProductPayload;

public class OrderReserveProductEvent extends Event<OrderReserveProductPayload> {
    public OrderReserveProductEvent(OrderReserveProductPayload payload) {
        super("OrderReserveProductEvent", payload);
    }
}
