package com.guifaleiros.orderservice.infrastructure.inbound.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {
    private String productName;
    private Integer productQuantity;
    private String productId;
}
