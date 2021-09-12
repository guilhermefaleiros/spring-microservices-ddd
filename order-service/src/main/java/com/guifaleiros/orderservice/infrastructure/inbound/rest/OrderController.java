package com.guifaleiros.orderservice.infrastructure.inbound.rest;

import com.guifaleiros.orderservice.application.domain.Order;
import com.guifaleiros.orderservice.application.services.OrderService;
import com.guifaleiros.orderservice.infrastructure.inbound.rest.dto.CreateOrderDTO;
import com.guifaleiros.orderservice.infrastructure.outbound.messaging.base.KafkaMessageProducer;
import com.guifaleiros.orderservice.infrastructure.utils.OrderAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class OrderController {

    private final OrderService orderService;
    private final OrderAssembler orderAssembler;

    @Autowired
    public OrderController(OrderService orderService, OrderAssembler orderAssembler) {
        this.orderService = orderService;
        this.orderAssembler = orderAssembler;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderDTO createOrderDTO) {
        var order = this.orderService.save(this.orderAssembler.toDomainFromDto(createOrderDTO));
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
