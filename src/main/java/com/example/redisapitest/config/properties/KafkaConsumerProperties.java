package com.example.redisapitest.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class KafkaConsumerProperties {

    /**
     * kafka集群信息
     * ,分隔IP
     */
    private static String bootstrapServers;

    /**
     * 心跳包间隔
     */
    private static String heartBeatInterval;

    /**
     * 消费者组ID
     */
    private static String groupId;

    /**
     * KEY反序列化
     */
    private static String keyDeserializer;

    /**
     * VALUE反序列化
     */
    private static String valueDeserializer;

    /**
     * 是否开启自动提交
     */
    private static String enableAutoCommit;

    /**
     * 自动提交间隔时间
     */
    private static String autoCommitInterval;

    /**
     * offset丢失，或者出现新的消费者组时，消费位置
     *
     */
    private static String autoOffsetReset;

    public static String getBootstrapServers() {
        return bootstrapServers;
    }

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    public void setBootstrapServers(String bootstrapServers) {
        KafkaConsumerProperties.bootstrapServers = bootstrapServers;
    }

    public static String getHeartBeatInterval() {
        return heartBeatInterval;
    }

    @Value("${spring.kafka.consumer.heartbeat-interval}")
    public void setHeartBeatInterval(String heartBeatInterval) {
        KafkaConsumerProperties.heartBeatInterval = heartBeatInterval;
    }

    public static String getGroupId() {
        return groupId;
    }

    @Value("${spring.kafka.consumer.group-id}")
    public void setGroupId(String groupId) {
        KafkaConsumerProperties.groupId = groupId;
    }

    public static String getKeyDeserializer() {
        return keyDeserializer;
    }

    @Value("${spring.kafka.consumer.key-deserializer}")
    public void setKeyDeserializer(String keyDeserializer) {
        KafkaConsumerProperties.keyDeserializer = keyDeserializer;
    }

    public static String getValueDeserializer() {
        return valueDeserializer;
    }

    @Value("${spring.kafka.consumer.value-deserializer}")
    public void setValueDeserializer(String valueDeserializer) {
        KafkaConsumerProperties.valueDeserializer = valueDeserializer;
    }

    public static String getEnableAutoCommit() {
        return enableAutoCommit;
    }

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    public void setEnableAutoCommit(String enableAutoCommit) {
        KafkaConsumerProperties.enableAutoCommit = enableAutoCommit;
    }

    public static String getAutoCommitInterval() {
        return autoCommitInterval;
    }

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    public void setAutoCommitInterval(String autoCommitInterval) {
        KafkaConsumerProperties.autoCommitInterval = autoCommitInterval;
    }

    public static String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    public void setAutoOffsetReset(String autoOffsetReset) {
        KafkaConsumerProperties.autoOffsetReset = autoOffsetReset;
    }
}
