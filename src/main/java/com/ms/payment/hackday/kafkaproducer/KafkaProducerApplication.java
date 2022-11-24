package com.ms.payment.hackday.kafkaproducer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class KafkaProducerApplication {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "pkc-56d1g.eastus.azure.confluent.cloud:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");

        // Replace the key and password below.
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='DUMMY_KEY_SSTPQKAUBDEDHJZO7' password='DUMMY_PASSWORD_ssyw34YAg4MWWzuYHajQn+Ft1JCSqBrkmd0DIWhfIDavhGoVq3ht94MmTbAIyLrw4';");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 100; i++) {
            var key = UUID.randomUUID().toString();
            var value = MessageString.PACS008.replace("_MESSAGE_KEY_", key);
            producer.send(new ProducerRecord<>("safety_outbound", key, value));
            System.out.println("Message sent : " + value);
        }
        producer.flush();
        producer.close();

    }

}

