package com.guifaleiros.orderservice.application.sagas.impl;

import com.guifaleiros.core.events.*;
import com.guifaleiros.core.events.base.Event;
import com.guifaleiros.core.events.model.payment.OrderExecutePaymentPayload;
import com.guifaleiros.core.events.model.product.OrderReserveProductCompensationPayload;
import com.guifaleiros.core.events.model.product.OrderReserveProductItem;
import com.guifaleiros.orderservice.application.domain.Order;
import com.guifaleiros.orderservice.application.domain.enums.OrderStatus;
import com.guifaleiros.orderservice.application.ports.JsonHandlerPort;
import com.guifaleiros.orderservice.application.ports.MessageProducerPort;
import com.guifaleiros.orderservice.application.ports.OrderRepositoryPort;
import com.guifaleiros.orderservice.application.sagas.OrderSaga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderSagaImpl implements OrderSaga {

    private final MessageProducerPort messageProducer;
    private final OrderRepositoryPort orderRepository;
    private final JsonHandlerPort jsonHandler;

    private final Logger Logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrderSagaImpl(MessageProducerPort messageProducer, OrderRepositoryPort orderRepository, JsonHandlerPort jsonHandler) {
        this.messageProducer = messageProducer;
        this.orderRepository = orderRepository;
        this.jsonHandler = jsonHandler;
    }

    @Override
    public void on(OrderReserveProductSuccessEvent event) {
        Order order = null;
        try {
            order = this.orderRepository.findById(event.getPayload().getOrderId());
            order.setStatus(OrderStatus.PENDING);
            order.setStatusMessage("Produto reservado com sucesso!");

            var paymentEvent = new OrderExecutePaymentEvent(new OrderExecutePaymentPayload(order.getId(), order.getUserId(), order.getTotal()));
            this.messageProducer.publish("order.payment.execute-payment", this.jsonHandler.serialize(paymentEvent), null);
            Logger.info("[ORDER-SERVICE] OrderReserveProductSuccessEvent with ID: [{}] handled", event.getEventId());
        } catch (Exception exception) {
            errorOn(order, exception, event);
        } finally {
            if (order != null) this.orderRepository.save(order);
        }
    }

    @Override
    public void on(OrderReserveProductFailedEvent event) {
        Order order = null;
        try {
            order = this.orderRepository.findById(event.getPayload().getOrderId());
            order.setStatus(OrderStatus.DENIED);
            order.setStatusMessage(event.getPayload().getMessage());

            Logger.info("[ORDER-SERVICE] OrderReserveProductFailedEvent with ID: [{}] handled", event.getEventId());
        } catch (Exception exception) {
            exception.printStackTrace();
            errorOn(order, exception, event);
        } finally {
            if (order != null) {
                this.orderRepository.save(order);
            }
        }
    }

    @Override
    public void on(OrderExecutePaymentSuccessEvent event) {
        Order order = null;
        try {
            order = this.orderRepository.findById(event.getPayload().getOrderId());
            order.setStatus(OrderStatus.APPROVED);
            order.setStatusMessage("Pagamento aprovado!");

            Logger.info("[ORDER-SERVICE] OrderExecutePaymentSuccessEvent with ID: [{}] handled", event.getEventId());
        } catch (Exception exception) {
            errorOn(order, exception, event);
        } finally {
            if (order != null) this.orderRepository.save(order);
        }
    }

    @Override
    public void on(OrderExecutePaymentFailedEvent event) {
        Order order = null;
        try {
            order = this.orderRepository.findById(event.getPayload().getOrderId());
            order.setStatus(OrderStatus.DENIED);
            order.setStatusMessage(event.getPayload().getMessage());

            var productCompensationEvent = this.jsonHandler.serialize(new
                    OrderReserveProductCompensationEvent(
                    new OrderReserveProductCompensationPayload(order.getId(),
                            order.getItems().stream().map(item ->
                                    new OrderReserveProductItem(item.getProductId(), item.getProductQuantity())).collect(Collectors.toList()))));
            this.messageProducer.publish("order.product.reserve-product-compensation", productCompensationEvent, null);
            Logger.info("[ORDER-SERVICE] OrderExecutePaymentFailedEvent with ID: [{}] handled", event.getEventId());
        } catch (Exception exception) {
            errorOn(order, exception, event);
        } finally {
            if (order != null) this.orderRepository.save(order);
        }
    }

    private void errorOn(Order order, Exception exception, Event event) {
        if (order != null) {
            order.setStatus(OrderStatus.FAILED);
            order.setStatusMessage("An internal error occurred");
            Logger.info("[ORDER-SAGA] {} with ID: [{}] failed. " +
                    "Cause: {}", event.getEventName(), event.getEventId(), exception.getMessage());
        } else {
            Logger.info("[ORDER-SAGA] {} with ID: [{}] failed. " +
                    "Cause: Order null", event.getEventName(), event.getEventId());
        }
    }
}
