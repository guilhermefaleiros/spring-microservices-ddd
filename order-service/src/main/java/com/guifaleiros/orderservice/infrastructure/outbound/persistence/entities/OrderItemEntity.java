package com.guifaleiros.orderservice.infrastructure.outbound.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    private String id;

    private String productName;

    private Integer productQuantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private String productId;
}
