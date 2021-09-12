package com.guifaleiros.orderservice.application.sagas;

import com.guifaleiros.core.events.OrderExecutePaymentFailedEvent;
import com.guifaleiros.core.events.OrderExecutePaymentSuccessEvent;
import com.guifaleiros.core.events.OrderReserveProductFailedEvent;
import com.guifaleiros.core.events.OrderReserveProductSuccessEvent;

public interface OrderSaga {
    void on(OrderReserveProductSuccessEvent event);
    void on(OrderReserveProductFailedEvent event);
    void on(OrderExecutePaymentSuccessEvent event);
    void on(OrderExecutePaymentFailedEvent event);
}
