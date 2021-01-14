package com.example.redisapitest.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Service
public class KafkaConsumerServiceImp {

    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Spring-Kafka 消费者 自动提交
     *
     * @param record 消息 ConsumerRecord
     * @param topic  订阅主题
     */
    @KafkaListener(topics = "test-topic-01", groupId = "test", concurrency = "3", topicPartitions = {@TopicPartition(topic = "test-topic-01", partitions = "0")})
    public void pollMessageFromKafkaAuto(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional.ofNullable(record.value()).ifPresent(message -> {
            log.info("topic_test 消费了： Topic:" + topic + ",Message:" + message);
        });
    }

    /**
     * Spring-Kafka 消费者 手动提交
     * @param record 消费消息具体信息
     * @param ack ack消费结果信息位
     * @param topic 消费主题
     */
//    @KafkaListener(topics = "test-topic-01", groupId = "test", concurrency = "3", topicPartitions = {@TopicPartition(topic = "test-topic-01", partitions = "0")})
    public void pollMessageFromKafkaManual(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional.ofNullable(record.value()).ifPresent(message -> {
            log.info("topic_test 消费了： Topic:" + topic + ",Message:" + message);
            //手动提交设置
            ack.acknowledge();
        });
    }
}
