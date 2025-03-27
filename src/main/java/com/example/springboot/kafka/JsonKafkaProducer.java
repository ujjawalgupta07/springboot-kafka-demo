package com.example.springboot.kafka;

import com.example.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    @Value("${spring.kafka.topic-json.name}")
    private String jsonTopicName;

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceJsonMessage(User user) {

        LOGGER.info("Sending json message to Kafka" + user.toString());

        Message<User> message = MessageBuilder
                                    .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, jsonTopicName)
                                .build();

        kafkaTemplate.send(message);

    }


}
