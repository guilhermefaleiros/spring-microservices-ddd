package com.guifaleiros.orderservice.application.services.impl;

import com.guifaleiros.core.events.OrderReserveProductEvent;
import com.guifaleiros.core.events.model.product.OrderReserveProductItem;
import com.guifaleiros.core.events.model.product.OrderReserveProductPayload;
import com.guifaleiros.orderservice.application.domain.Order;
import com.guifaleiros.orderservice.application.ports.JsonHandlerPort;
import com.guifaleiros.orderservice.application.ports.MessageProducerPort;
import com.guifaleiros.orderservice.application.ports.OrderRepositoryPort;
import com.guifaleiros.orderservice.application.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryPort orderRepository;
    private final MessageProducerPort messageProducer;
    private final JsonHandlerPort jsonHandler;

    @Autowired
    public OrderServiceImpl(OrderRepositoryPort orderRepository, MessageProducerPort messageProducer, JsonHandlerPort jsonHandler) {
        this.orderRepository = orderRepository;
        this.messageProducer = messageProducer;
        this.jsonHandler = jsonHandler;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        Order result = this.orderRepository.save(order);
        var event = new OrderReserveProductEvent(new OrderReserveProductPayload(order.getId(),
                order.getItems().stream().map(item -> new OrderReserveProductItem(item.getProductId(),
                        item.getProductQuantity())).collect(Collectors.toList())));

        messageProducer.publish("order.product.reserve-product", jsonHandler.serialize(event), null);
        return result;
    }
}
