package com.guifaleiros.core.events;

import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.product.OrderReserveProductSuccessPayload;

public class OrderReserveProductSuccessEvent extends Event<OrderReserveProductSuccessPayload> {
    public OrderReserveProductSuccessEvent(OrderReserveProductSuccessPayload payload) {
        super("OrderReserveProductSuccessEvent", payload);
    }
}
