package com.example.redisapitest.utils;

import com.example.redisapitest.config.properties.KafkaConsumerProperties;
import com.example.redisapitest.config.properties.KafkaProducerProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


public class KafkaUtils {

    private static final Properties producerProps;

    private static final Properties consumerProps;

    //设置生产者参数
    static {
        producerProps = new Properties();
        producerProps.put("bootstrap.servers", KafkaProducerProperties.getBootstrapServers());
        producerProps.put("acks", KafkaProducerProperties.getAcks());
        producerProps.put("retries", KafkaProducerProperties.getRetries());
        producerProps.put("linger.ms", KafkaProducerProperties.getLingerMills());
        producerProps.put("key.serializer", KafkaProducerProperties.getKeySerializer());
        producerProps.put("value.serializer", KafkaProducerProperties.getValueSerializer());
    }

    //设置消费者参数
    static {
        consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", KafkaConsumerProperties.getBootstrapServers());
        consumerProps.put("group.id", KafkaConsumerProperties.getGroupId());
        consumerProps.put("enable.auto.commit", KafkaConsumerProperties.getEnableAutoCommit());
        consumerProps.put("auto.commit.interval.ms", KafkaConsumerProperties.getAutoCommitInterval());
        consumerProps.put("key.deserializer", KafkaConsumerProperties.getKeyDeserializer());
        consumerProps.put("value.deserializer", KafkaConsumerProperties.getValueDeserializer());
    }

    /**
     * 生产者生产数据
     */
    public static void generateMessageToKafka() {
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(producerProps);
        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("test-topic-01", LocalDateTime.now().toString() + i, LocalDateTime.now().toString() + i);
            kafkaProducer.send(record, (recordMetadata, e) -> {
                String message = Optional.ofNullable(e)
                        .map(Throwable::getMessage)
                        .orElse(recordMetadata.toString());
                System.out.println(message);
            });
        }
        kafkaProducer.close();
    }

    /**
     * 消费数据 (手动提交异步)
     */
    public static void pollMessageFromKafkaManual() {
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(consumerProps);
        kafkaConsumer.subscribe(Collections.singletonList("test-topic-01"));
        int count = 0;
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                System.out.println("拉去消息：" + records.count() + "条");
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("成功消费消息：topic = %s ,partition = %d,offset = %d, key = %s, value = %s%n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    offsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));
                    if (count % 100 == 0) {
                        kafkaConsumer.commitAsync(offsets, (offsetMap, e) -> {
                            String message = Optional.ofNullable(e)
                                    .map(Throwable::getMessage)
                                    .orElse("异步提交成功");
                            System.out.println(message);
                        });
                    }
                    count++;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // 最后一次提交使用同步阻塞式提交（确保消费者位移正确性）
            kafkaConsumer.commitSync();
            kafkaConsumer.close();
        }
    }

    /**
     * 消费消息（自动提交）
     */
    public static void pollMessageFromKafkaAuto() {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(consumerProps);
        kafkaConsumer.subscribe(Collections.singletonList("test-topic-01"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            System.out.println("拉去消息：" + records.count() + "条");
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("成功消费消息：topic = %s ,partition = %d,offset = %d, key = %s, value = %s%n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }



}

