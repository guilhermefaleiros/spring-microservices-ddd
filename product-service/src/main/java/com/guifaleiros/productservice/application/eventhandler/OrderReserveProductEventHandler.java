package com.guifaleiros.productservice.application.eventhandler;

import com.guifaleiros.core.events.OrderReserveProductCompensationEvent;
import com.guifaleiros.core.events.OrderReserveProductEvent;

public interface OrderReserveProductEventHandler {
    void on(OrderReserveProductEvent event);
    void on(OrderReserveProductCompensationEvent event);
}
