package com.ms.payments.servicebus;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class ServiceBusForwarder implements CommandLineRunner {


    // Kafka connection parameters
    @Value("${boot.strap.servers}")
    String bootStrapServers;
    @Value("${sasl.jaas.config}")
    String saslJaasConfig;
    @Value("${security.protocol}")
    String securityProtocol;
    @Value("${sasl.mechanism}")
    String saslMechanism;
    @Value("${key.deserializer}")
    String keyDeserializer;
    @Value("${value.deserializer}")
    String valueDeserializer;
    @Value("${consumer.group.id}")
    String consumerGroupId;
    @Value("${kafka.input.topic}")
    String inputTopic;


    KafkaConsumer<String, String> consumer;

    // Service Bus connection parameters
    @Value("${servicebus.connection.string}")
    private String connectionString;
    @Value("${servicebus.queue.name}")
    private String queueName;

    ServiceBusSenderClient senderClient;

    @PostConstruct
    private void init() {
        // initialize producer
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootStrapServers);
        properties.setProperty("security.protocol", securityProtocol);
        properties.setProperty("sasl.mechanism", saslMechanism);
        properties.setProperty("sasl.jaas.config", saslJaasConfig);
        properties.setProperty("key.deserializer", keyDeserializer);
        properties.setProperty("value.deserializer", valueDeserializer);
        properties.setProperty("group.id", consumerGroupId);
        properties.setProperty("auto.offset.reset", "earliest");


        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(List.of(inputTopic));

        // create a Service Bus Sender client for the queue
        senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

    }

    @Override
    public void run(String... args) {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                // send one message to the queue
                senderClient.sendMessage(new ServiceBusMessage(record.value()));
                log.info("Sent a single message. key: " + record.key());
            }

        }
    }
}
