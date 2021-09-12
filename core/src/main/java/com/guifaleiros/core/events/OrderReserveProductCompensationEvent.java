package com.guifaleiros.core.events;

import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.product.OrderReserveProductCompensationPayload;

public class OrderReserveProductCompensationEvent extends Event<OrderReserveProductCompensationPayload> {
    public OrderReserveProductCompensationEvent(OrderReserveProductCompensationPayload payload) {
        super("OrderReserveProductCompensationEvent", payload);
    }
}
