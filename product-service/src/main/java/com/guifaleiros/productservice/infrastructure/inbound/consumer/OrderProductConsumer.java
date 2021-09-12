package com.guifaleiros.productservice.infrastructure.inbound.consumer;

import com.guifaleiros.core.events.OrderReserveProductCompensationEvent;
import com.guifaleiros.core.events.OrderReserveProductEvent;
import com.guifaleiros.core.ports.JsonHandlerPort;
import com.guifaleiros.productservice.application.eventhandler.OrderReserveProductEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderProductConsumer {

    final OrderReserveProductEventHandler eventHandler;
    final JsonHandlerPort jsonHandler;

    @Autowired
    public OrderProductConsumer(OrderReserveProductEventHandler eventHandler,
                                JsonHandlerPort jsonHandler) {
        this.eventHandler = eventHandler;
        this.jsonHandler = jsonHandler;
    }

    @KafkaListener(topics = "order.product.reserve-product")
    public void onOrderReserveProductEvent(@Payload String message,
                                           @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                           Acknowledgment ack) {
        var event = this.jsonHandler.deserialize(message, OrderReserveProductEvent.class);
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "order.product.reserve-product-compensation")
    public void onOrderReserveProductCompensationEvent(@Payload String message,
                                           @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                           Acknowledgment ack) {
        var event = this.jsonHandler.deserialize(message, OrderReserveProductCompensationEvent.class);
        eventHandler.on(event);
        ack.acknowledge();
    }
}
