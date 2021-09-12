package com.guifaleiros.orderservice.infrastructure.inbound.consumer;

import com.guifaleiros.core.events.OrderExecutePaymentFailedEvent;
import com.guifaleiros.core.events.OrderExecutePaymentSuccessEvent;
import com.guifaleiros.core.events.OrderReserveProductFailedEvent;
import com.guifaleiros.core.events.OrderReserveProductSuccessEvent;
import com.guifaleiros.orderservice.application.ports.JsonHandlerPort;
import com.guifaleiros.orderservice.application.sagas.OrderSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderSagaConsumer {

    final OrderSaga orderSaga;

    final JsonHandlerPort jsonHandler;

    @Autowired
    public OrderSagaConsumer(OrderSaga orderSaga, JsonHandlerPort jsonHandler) {
        this.orderSaga = orderSaga;
        this.jsonHandler = jsonHandler;
    }

    @KafkaListener(topics = "order.product.reserve-product-success")
    public void onOrderReserveProductSuccessEvent(@Payload String message,
                                                  @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                                  Acknowledgment ack) {
        var event = this.jsonHandler.deserialize(message, OrderReserveProductSuccessEvent.class);
        this.orderSaga.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "order.product.reserve-product-failed")
    public void onOrderReserveProductFailedEvent(@Payload String message,
                                                 @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                                 Acknowledgment ack) {
        var event = this.jsonHandler.deserialize(message, OrderReserveProductFailedEvent.class);
        this.orderSaga.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "order.payment.execute-payment-success")
    public void onOrderExecutePaymentSuccessEvent(@Payload String message,
                                                  @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                                  Acknowledgment ack) {
        var event = this.jsonHandler.deserialize(message, OrderExecutePaymentSuccessEvent.class);
        this.orderSaga.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "order.payment.execute-payment-failed")
    public void onOrderExecutePaymentFailedEvent(@Payload String message,
                                                 @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                                 Acknowledgment ack) {
        var event = this.jsonHandler.deserialize(message, OrderExecutePaymentFailedEvent.class);
        this.orderSaga.on(event);
        ack.acknowledge();
    }
}
