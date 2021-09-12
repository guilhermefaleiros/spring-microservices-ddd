package com.guifaleiros.productservice.application.ports.messaging;

public interface MessageProducerPort {
    void publish(String topicOrQueue, String body, String key);
}
