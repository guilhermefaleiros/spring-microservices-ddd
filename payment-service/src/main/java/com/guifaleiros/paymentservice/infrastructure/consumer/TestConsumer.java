package com.guifaleiros.paymentservice.infrastructure.consumer;

import com.guifaleiros.core.events.OrderExecutePaymentEvent;
import com.guifaleiros.core.events.OrderExecutePaymentFailedEvent;
import com.guifaleiros.core.events.OrderExecutePaymentSuccessEvent;
import com.guifaleiros.core.events.model.payment.OrderExecutePaymentFailedPayload;
import com.guifaleiros.core.events.model.payment.OrderExecutePaymentSuccessPayload;
import com.guifaleiros.core.ports.JsonHandlerPort;
import com.guifaleiros.core.ports.MessageProducerPort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    private final MessageProducerPort messageProducer;
    private final JsonHandlerPort jsonHandler;

    public TestConsumer(MessageProducerPort messageProducer, JsonHandlerPort jsonHandler) {
        this.messageProducer = messageProducer;
        this.jsonHandler = jsonHandler;
    }

    @KafkaListener(topics = "order.payment.execute-payment")
    public void handler(@Payload String message,
                                   @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                   Acknowledgment ack) {
        var event = jsonHandler.deserialize(message, OrderExecutePaymentEvent.class);

//        var successEvent = this.jsonHandler.serialize(new OrderExecutePaymentSuccessEvent(
//                new OrderExecutePaymentSuccessPayload(event.getPayload().getOrderId())
//        ));
//
//        messageProducer.publish("order.payment.execute-payment-success", successEvent, null);

        var failedEvent = this.jsonHandler.serialize(new OrderExecutePaymentFailedEvent(
                new OrderExecutePaymentFailedPayload(
                        event.getPayload().getOrderId(),
                        "Falha ao realizar pagamento!"
                )
        ));
        messageProducer.publish("order.payment.execute-payment-failed", failedEvent, null);
        System.out.println(event);
        ack.acknowledge();
    }
}
