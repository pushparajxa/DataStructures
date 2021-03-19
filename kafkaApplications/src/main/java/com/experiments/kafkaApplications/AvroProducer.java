/*
 * Copyright 2021 DigitalRoute Route AB. All rights reserved.
 *  Proprietary and Confidential.
 */
package com.experiments.kafkaApplications;

import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.stream.IntStream;

public class AvroProducer {
    
    private static final  Logger logger =
        LoggerFactory.getLogger(AvroProducer.class);
    
    static HashMap<String, String> map = new HashMap<>();
    
    static {
        map.put("dss", "asdas");
    }
    
    
    private  Producer<Long, Employee> createProducer()
    {
        logger.info("Creating a producer");
        
        Properties props = new Properties();
/*        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // Schema Registry location.
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
                "http://localhost:8081");*/
        
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            Driver.getBootStrapServers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "com.deshaw.guas.AvroProducer");
        
        
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            KafkaAvroSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            KafkaAvroSerializer.class.getName());
        
        // Schema Registry location.
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
            Driver.getSchemaRegistryUrl());
        
/*      props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.kerberos.service.name", "kafka");
        props.put("sasl.mechanism", "GSSAPI");
        props.put("sasl.kerberos.kinit.cmd", "/usr/local/bin/skinit --quiet");
        */
        return new KafkaProducer<>(props);
    }
    
    
    public  void exec() {
        
        Producer<Long, Employee> producer = createProducer();
        String topic = Driver.getTopic();
        
        Employee bob = Employee.newBuilder()
            .setAge(35)
            .setFirstName("Daniel")
            .setLastName("Jones")
            .setPhoneNumber(
                PhoneNumber.newBuilder()
                    .setAreaCode("301")
                    .setCountryCode("1")
                    .setPrefix("555")
                    .setNumber("1234")
                    .build()
            ).build();
        
        IntStream.range(1, 10).forEach(index->{
            logger.info("Producing records");
            bob.setFirstName("Daniel" + System.currentTimeMillis());
            bob.setLastName("Jones"+ LocalDateTime.now().getMinute());
            producer.send(new ProducerRecord<>(Driver.getTopic(), System.currentTimeMillis(), bob));
        });
        
        logger.info("Shutting down the producer");
        producer.flush();
        producer.close();
    }
    
}

