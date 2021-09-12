package com.guifaleiros.productservice.application.eventhandler.impl;

import com.guifaleiros.core.events.OrderReserveProductCompensationEvent;
import com.guifaleiros.core.events.OrderReserveProductEvent;
import com.guifaleiros.core.events.OrderReserveProductFailedEvent;
import com.guifaleiros.core.events.OrderReserveProductSuccessEvent;
import com.guifaleiros.core.events.model.product.OrderReserveProductFailedPayload;
import com.guifaleiros.core.events.model.product.OrderReserveProductItem;
import com.guifaleiros.core.events.model.product.OrderReserveProductSuccessPayload;
import com.guifaleiros.core.ports.JsonHandlerPort;
import com.guifaleiros.productservice.application.domain.Product;
import com.guifaleiros.productservice.application.eventhandler.OrderReserveProductEventHandler;
import com.guifaleiros.productservice.application.ports.messaging.MessageProducerPort;
import com.guifaleiros.productservice.application.ports.persistence.ProductRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderReserveProductEventHandlerImpl implements OrderReserveProductEventHandler {

    final ProductRepositoryPort productRepository;
    final MessageProducerPort messageProducer;
    final JsonHandlerPort jsonHandler;

    @Autowired
    public OrderReserveProductEventHandlerImpl(ProductRepositoryPort productRepository,
                                               MessageProducerPort messageProducer,
                                               JsonHandlerPort jsonHandler) {
        this.productRepository = productRepository;
        this.messageProducer = messageProducer;
        this.jsonHandler = jsonHandler;
    }

    @Override
    public void on(OrderReserveProductEvent event) {
        try {
            var payload = event.getPayload();
            var productsToUpdate = getAvailableProductsFromOrder(event.getPayload().getItems());

            if (productsToUpdate.size() < payload.getItems().size()) {
                var failedEvent = this.jsonHandler.serialize(new OrderReserveProductFailedEvent(
                        new OrderReserveProductFailedPayload(
                                event.getPayload().getOrderId(),
                                "Estoque dos produtos desejados não disponível"
                        )));
                this.messageProducer.publish("order.product.reserve-product-failed", failedEvent, null);
            } else {
                productsToUpdate.stream().forEach(product -> {
                    var quantityToSubtract = payload
                            .getItems()
                            .stream()
                            .filter(item -> item.getProductId().equals(product.getId()))
                            .findFirst()
                            .get()
                            .getQuantity();

                    var currentQuantity = product.getQuantity();
                    product.setQuantity(currentQuantity - quantityToSubtract);
                    this.productRepository.save(product);
                });

                var successEvent = this.jsonHandler.serialize(new OrderReserveProductSuccessEvent(
                        new OrderReserveProductSuccessPayload(
                                event.getPayload().getOrderId(),
                                event.getPayload().getItems()
                        )));
                this.messageProducer.publish("order.product.reserve-product-success", successEvent, null);
            }
        } catch (Exception exception) {
            var failedEvent = this.jsonHandler.serialize(new OrderReserveProductFailedEvent(
                    new OrderReserveProductFailedPayload(
                            event.getPayload().getOrderId(),
                            "Estoque dos produtos desejados não disponível"
                    )));
            this.messageProducer.publish("order.product.reserve-product-failed", failedEvent, null);
        }
    }

    @Override
    public void on(OrderReserveProductCompensationEvent event) {
        try {
            var payload = event.getPayload();
            var productsToUpdate = getProductsFromOrder(event.getPayload().getItems());

            productsToUpdate.stream().forEach(product -> {
                var quantityToAdd = payload
                        .getItems()
                        .stream()
                        .filter(item -> item.getProductId().equals(product.getId()))
                        .findFirst()
                        .get()
                        .getQuantity();

                var currentQuantity = product.getQuantity();
                product.setQuantity(currentQuantity + quantityToAdd);
                this.productRepository.save(product);
            });
        }catch (Exception exception) {

        }
    }

    private List<Product> getAvailableProductsFromOrder(List<OrderReserveProductItem> items) {
        List<Product> productsToUpdate = new ArrayList<>();
        items.forEach(item -> {
            var product = this.productRepository.findById(item.getProductId());
            if (product != null && product.getQuantity() >= item.getQuantity()) {
                productsToUpdate.add(product);
            }
        });
        return productsToUpdate;
    }

    private List<Product> getProductsFromOrder(List<OrderReserveProductItem> items) {
        List<Product> productsToUpdate = new ArrayList<>();
        items.forEach(item -> {
            var product = this.productRepository.findById(item.getProductId());
            if (product != null) {
                productsToUpdate.add(product);
            }
        });
        return productsToUpdate;
    }

}
