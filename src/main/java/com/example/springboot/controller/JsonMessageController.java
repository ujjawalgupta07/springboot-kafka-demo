package com.example.springboot.controller;

import com.example.springboot.dto.User;
import com.example.springboot.kafka.JsonKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private final JsonKafkaProducer jsonKafkaProducer;

    public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("/publish-json")
    public ResponseEntity<String> publishJson(@RequestBody User user) {
        jsonKafkaProducer.produceJsonMessage(user);
        return ResponseEntity.ok("Json message published");
    }
}
