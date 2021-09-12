package com.guifaleiros.orderservice.application.ports;

public interface MessageProducerPort {
    void publish(String topicOrQueue, String body, String key);
}
