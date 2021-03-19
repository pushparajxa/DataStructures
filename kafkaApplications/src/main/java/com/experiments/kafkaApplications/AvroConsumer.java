/*
 * Copyright 2021 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.experiments.kafkaApplications;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroConsumer {
    
    private static final Logger logger =
        LoggerFactory.getLogger(AvroConsumer.class);

    
    private Consumer<Long, Employee> createConsumer(
        String consumerGroupId
    )
    {
        Properties props = new Properties();
        //props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
         /* props.put(ConsumerConfig.GROUP_ID_CONFIG,
            "motamari_schema_registry_test_consumer_group_1");*/
        /* props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
                "http://localhost:8081"); //<----- Run Schema Registry on 8081*/
        
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            Driver.getBootStrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
            Driver.getSchemaRegistryUrl());
        
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            KafkaAvroDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            KafkaAvroDeserializer.class.getName());  //<----------------------
        
        //Use Specific Record or else you get Avro GenericRecord.
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG,
            "true");
        
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.kerberos.service.name", "kafka");
        props.put("sasl.mechanism", "GSSAPI");
        props.put("sasl.kerberos.kinit.cmd", "/usr/local/bin/skinit --quiet");
        
        return new KafkaConsumer<>(props);
    }
    
    public  void exec(String consumerGroupId) {
        
        final Consumer<Long, Employee> consumer =
            createConsumer(consumerGroupId);
        consumer.subscribe(Collections.singletonList(Driver.getTopic()));
        
        IntStream.range(1, 20).forEach(index -> {
            
            final ConsumerRecords<Long, Employee> records =
                consumer.poll(10000);
            
            if (records.count() == 0) {
                logger.info("No records found");
            } else records.forEach(record -> {
                Employee employeeRecord = record.value();
                
                logger.info(
                    String.format( "%s %d %d %s \n", record.topic(),
                        record.partition(), record.offset(), employeeRecord));
            });
        });
    }
    
    
}