package com.ms.payments.kafka.producer;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;


@Component
public class KafkaProducerApplication implements CommandLineRunner {

    KafkaProducer<String, String> producer;

    @PostConstruct
    private void init() {
        var properties = com.ms.payments.kafka.config.KafkaConfigProps.getDefaultProps();

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        producer = new KafkaProducer<>(properties);
    }


    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {

        for (int i = 0; i < 100; i++) {
            var key = UUID.randomUUID().toString();
            var value = MessageString.PACS008.replace("_MESSAGE_KEY_", key);
            producer.send(new ProducerRecord<>("safety_outbound", key, value));
            System.out.println("Message sent : " + i);
        }
        producer.flush();
        producer.close();

    }

}

