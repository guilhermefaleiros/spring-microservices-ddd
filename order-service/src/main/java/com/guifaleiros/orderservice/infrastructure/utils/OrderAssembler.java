package com.guifaleiros.orderservice.infrastructure.utils;

import com.guifaleiros.orderservice.application.domain.Order;
import com.guifaleiros.orderservice.application.domain.OrderItem;
import com.guifaleiros.orderservice.application.domain.enums.OrderStatus;
import com.guifaleiros.orderservice.infrastructure.inbound.rest.dto.CreateOrderDTO;
import com.guifaleiros.orderservice.infrastructure.outbound.persistence.entities.OrderEntity;
import com.guifaleiros.orderservice.infrastructure.outbound.persistence.entities.OrderItemEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class OrderAssembler {

    @PersistenceContext
    EntityManager entityManager;


    public Order toDomainFromEntity(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getTotal(),
                entity.getUserId(),
                entity.getDate(),
                entity.getItems().stream().map(item -> new OrderItem(item.getId(),
                        item.getProductName(),
                        item.getProductQuantity(),
                        item.getProductId())).collect(Collectors.toList()),
                entity.getStatus(),
                entity.getStatusMessage()
        );
    }

    public OrderEntity toEntityFromDomain(Order order) {
        var orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setTotal(order.getTotal());
        orderEntity.setUserId(order.getUserId());
        orderEntity.setDate(order.getDate());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setStatusMessage(order.getStatusMessage());

        order.getItems().forEach(item -> {
            var orderItem = OrderItemEntity.builder()
                    .id(item.getId())
                    .productId(item.getProductId())
                    .productName(item.getProductName())
                    .productQuantity(item.getProductQuantity())
                    .order(orderEntity)
                    .build();
            orderEntity.getItems().add(orderItem);
        });

        return orderEntity;
    }

    public Order toDomainFromDto(CreateOrderDTO dto) {
        return new Order(
                dto.getTotal(),
                dto.getUserId(),
                LocalDateTime.now(),
                dto.getItems().stream().map(item -> new OrderItem(
                        item.getProductName(),
                        item.getProductQuantity(),
                        item.getProductId()
                )).collect(Collectors.toList()),
                OrderStatus.PENDING,
                "Pedido criado com sucesso. Aguardando aprovação"
        );
    }

}
