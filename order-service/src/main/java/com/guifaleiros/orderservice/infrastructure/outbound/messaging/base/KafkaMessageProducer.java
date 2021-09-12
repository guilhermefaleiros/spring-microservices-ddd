package com.guifaleiros.orderservice.infrastructure.outbound.messaging.base;

import com.guifaleiros.orderservice.application.ports.MessageProducerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaMessageProducer implements MessageProducerPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String topic, String body, String key) {
        if(key == null) key = UUID.randomUUID().toString();
        this.kafkaTemplate.send(topic, key, body);
    }
}
