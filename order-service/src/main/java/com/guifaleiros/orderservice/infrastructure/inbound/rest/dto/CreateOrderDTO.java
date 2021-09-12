package com.guifaleiros.orderservice.infrastructure.inbound.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {
    private String userId;
    private Double total;
    private List<OrderItemDTO> items;


}
