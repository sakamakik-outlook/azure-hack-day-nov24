package com.ms.payments.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfigProps {

    @Value("${bootstrap.servers}")
    String bootStrapServers;

    public Properties getDefaultProps(){

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootStrapServers);
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");

        // Replace the key and password below.
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='DUMMY_ID_SSTPQKAUBDEDHJZO7' password='DUMMY_ID_Ssyw34YAg4MWWzuYHajQn+Ft1JCSqBrkmd0DIWhfIDavhGoVq3ht94MmTbAIyLrw4';");

        return properties;

    }
}
