package com.guifaleiros.orderservice.infrastructure.outbound.persistence.entities;

import com.guifaleiros.orderservice.application.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    private String id;

    private Double total;

    private String userId;

    private LocalDateTime date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItemEntity> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String statusMessage;
}
