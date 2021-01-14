package com.example.redisapitest.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.producer")
public class KafkaProducerProperties {

    /**
     * 确保生产者可靠性设置，有三个选项：
     * acks=0:不等待成功返回
     * acks=1:等Leader写成功返回
     * acks=all:等Leader和所有ISR中的Follower写成功返回,all也可以用-1代替
     */
    private static String acks;

    /**
     * 每个partition的未发送消息大小
     */
    private static String batchSize;

    /**
     * Producer 缓冲区的总体内存大小（决定Producer可以传递消息的上限）
     */
    private static String bufferMemory;

    /**
     * 失败重试次数
     */
    private static Integer retries;

    /**
     * kafka集群信息
     * 不同IP ,号分割
     */
    private static String bootstrapServers;

    /**
     * key值序列化
     */
    private static String keySerializer;

    /**
     * value值序列化
     */
    private static String valueSerializer;

    /**
     * 数据延迟发送时长
     * 0：立即发送 太大导致缓冲区压力过大 太小请求过于频繁
     */
    private static Integer lingerMills;

    public static String getAcks() {
        return acks;
    }

    @Value("${spring.kafka.producer.acks}")
    public void setAcks(String acks) {
        KafkaProducerProperties.acks = acks;
    }

    public static String getBatchSize() {
        return batchSize;
    }

    @Value("${spring.kafka.producer.batch-size}")
    public void setBatchSize(String batchSize) {
        KafkaProducerProperties.batchSize = batchSize;
    }

    public static String getBufferMemory() {
        return bufferMemory;
    }

    @Value("${spring.kafka.producer.buffer-memory}")
    public void setBufferMemory(String bufferMemory) {
        KafkaProducerProperties.bufferMemory = bufferMemory;
    }

    public static Integer getRetries() {
        return retries;
    }

    @Value("${spring.kafka.producer.retries}")
    public void setRetries(Integer retries) {
        KafkaProducerProperties.retries = retries;
    }

    public static String getBootstrapServers() {
        return bootstrapServers;
    }

    @Value("${spring.kafka.producer.bootstrap-servers}")
    public void setBootstrapServers(String bootstrapServers) {
        KafkaProducerProperties.bootstrapServers = bootstrapServers;
    }

    public static String getKeySerializer() {
        return keySerializer;
    }

    @Value("${spring.kafka.producer.key-serializer}")
    public void setKeySerializer(String keySerializer) {
        KafkaProducerProperties.keySerializer = keySerializer;
    }

    public static String getValueSerializer() {
        return valueSerializer;
    }

    @Value("${spring.kafka.producer.value-serializer}")
    public void setValueSerializer(String valueSerializer) {
        KafkaProducerProperties.valueSerializer = valueSerializer;
    }

    public static Integer getLingerMills() {
        return lingerMills;
    }

    @Value("${spring.kafka.producer.properties.linger.ms}")
    public void setLingerMills(Integer lingerMills) {
        KafkaProducerProperties.lingerMills = lingerMills;
    }
}
