package com.ms.payment.hackday.kafkaproducer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;

import static com.ms.payment.hackday.kafkaproducer.KafkaConfigProps.getDefaultProps;

public class KafkaConsumerApplication {

    public static void main(String[] args) {

        var properties = getDefaultProps();

        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"kensaka-test-2");
        properties.setProperty("auto.offset.reset", "earliest");



        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList("db_writer"));


        while(true){
            ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String,String> record: records){
                System.out.println("Key: "+ record.key() + ", Value:" +record.value());
                System.out.println("Partition:" + record.partition()+",Offset:"+record.offset());
            }

        }

    }
}

