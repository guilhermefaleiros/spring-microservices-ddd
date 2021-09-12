package com.guifaleiros.orderservice.infrastructure.outbound.persistence;

import com.guifaleiros.orderservice.application.domain.Order;
import com.guifaleiros.orderservice.application.ports.OrderRepositoryPort;
import com.guifaleiros.orderservice.infrastructure.utils.OrderAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLOrderRepository implements OrderRepositoryPort {

    final SpringDataOrderRepository springDataOrderRepository;

    final OrderAssembler orderEntityAssembler;

    @Autowired
    public MySQLOrderRepository(SpringDataOrderRepository springDataOrderRepository, OrderAssembler orderEntityAssembler) {
        this.springDataOrderRepository = springDataOrderRepository;
        this.orderEntityAssembler = orderEntityAssembler;
    }

    @Override
    public Order save(Order order) {
        var orderEntity = this.orderEntityAssembler.toEntityFromDomain(order);
        this.springDataOrderRepository.save(orderEntity);
        return order;
    }

    @Override
    public Order findById(String id) {
        var orderEntity = this.springDataOrderRepository.findById(id);
        return this.orderEntityAssembler.toDomainFromEntity(orderEntity.get());
    }

}
