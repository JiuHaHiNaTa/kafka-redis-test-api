package com.example.redisapitest;

import com.example.redisapitest.config.properties.KafkaConsumerProperties;
import com.example.redisapitest.config.properties.KafkaProducerProperties;
import com.example.redisapitest.config.properties.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableConfigurationProperties({RedisProperties.class , KafkaProducerProperties.class , KafkaConsumerProperties.class})
@SpringBootApplication
public class RedisApiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApiTestApplication.class, args);
    }

}
