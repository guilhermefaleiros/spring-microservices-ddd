package com.guifaleiros.core.ports;

public interface MessageProducerPort {
    void publish(String topicOrQueue, String body, String key);
}
