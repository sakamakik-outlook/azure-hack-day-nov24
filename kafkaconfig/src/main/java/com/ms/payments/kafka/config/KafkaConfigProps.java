package com.ms.payments.kafka.config;

import java.util.Properties;

public class KafkaConfigProps {
    public static Properties getDefaultProps(){

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "pkc-56d1g.eastus.azure.confluent.cloud:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");

        // Replace the key and password below.
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='DUMMY_ID_SSTPQKAUBDEDHJZO7' password='DUMMY_ID_Ssyw34YAg4MWWzuYHajQn+Ft1JCSqBrkmd0DIWhfIDavhGoVq3ht94MmTbAIyLrw4';");

        return properties;

    }
}
