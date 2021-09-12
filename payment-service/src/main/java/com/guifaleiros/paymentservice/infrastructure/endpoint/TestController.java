package com.guifaleiros.paymentservice.infrastructure.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    TestController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/")
    public ResponseEntity test() {
        this.kafkaTemplate.send("test", UUID.randomUUID().toString(), "test");
        return ResponseEntity.ok().build();
    }

}
