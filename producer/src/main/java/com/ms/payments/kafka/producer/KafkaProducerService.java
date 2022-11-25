package com.ms.payments.kafka.producer;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


@Service
public class KafkaProducerService implements CommandLineRunner {

    @Value("${boot.strap.servers}")
    String bootStrapServers;

    @Value("${sasl.jaas.config}")
    String saslJaasConfig;

    @Value("${security.protocol}")
    String securityProtocol;

    @Value("${sasl.mechanism}")
    String saslMechanism;

    @Value("${key.serializer}")
    String keySerializer;

    @Value("${value.serializer}")
    String valueSerializer;

    @Value("${message.count}")
    int messageCount;

    @Value("${sleep.interval.milsec}")
    int sleepIntMilsec;


    KafkaProducer<String, String> producer;

    @PostConstruct
    private void init() {
        // initialize producer
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootStrapServers);
        properties.setProperty("security.protocol", securityProtocol);
        properties.setProperty("sasl.mechanism", saslMechanism);
        properties.setProperty("sasl.jaas.config", saslJaasConfig);
        properties.setProperty("key.serializer", keySerializer);
        properties.setProperty("value.serializer", valueSerializer);
        producer = new KafkaProducer<>(properties);
    }

    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {

        for (int i = 0; i < messageCount; i++) {
            var key = UUID.randomUUID().toString();
            var value = MXMessage.PACS008_TEMPLATE.replace("_MESSAGE_KEY_", key);
            producer.send(new ProducerRecord<>("safety_outbound", key, value)).get();
            System.out.println("Message sent : " + i);
            TimeUnit.MILLISECONDS.sleep(sleepIntMilsec);
        }

    }

}

