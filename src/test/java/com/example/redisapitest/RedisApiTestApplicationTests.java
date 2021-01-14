package com.example.redisapitest;

import com.example.redisapitest.config.properties.KafkaConsumerProperties;
import com.example.redisapitest.config.properties.KafkaProducerProperties;
import com.example.redisapitest.config.properties.RedisProperties;
import com.example.redisapitest.entity.User;
import com.example.redisapitest.service.KafkaConsumerServiceImp;
import com.example.redisapitest.service.KafkaProducerServiceImp;
import com.example.redisapitest.service.UserServiceImp;
import com.example.redisapitest.utils.KafkaUtils;
import com.example.redisapitest.utils.RedisUtils;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableConfigurationProperties({RedisProperties.class , KafkaProducerProperties.class , KafkaConsumerProperties.class})
@SpringBootTest
class RedisApiTestApplicationTests {


    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    KafkaProducerServiceImp kafkaProducerServiceImp;

    @Autowired
    KafkaConsumerServiceImp kafkaConsumerServiceImp;

    @Test
    void contextLoads() {
//
//        User user = userServiceImp.findUserById("0001");
//        String result = Optional.ofNullable(user)
//                .map(userTemp -> userTemp.getId() + "-" + userTemp.getUsername())
//                .orElse("查无此人");
//        System.out.println(result);
//        System.out.println(KafkaProducerProperties.getAcks());
        KafkaUtils.generateMessageToKafka();
//        KafkaUtils.pollMessageFromKafka();

//        kafkaProducerServiceImp.sendToKafka(user);
//        System.out.println(user.getId());
//        System.out.println(user.getUsername());
//        System.out.println(RedisProperties.getClientName());
//        System.out.println(RedisProperties.getPort());
//        System.out.println(RedisProperties.getMaxIdle());
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        list.parallelStream().forEach(temp -> {
//            System.out.println(Thread.currentThread().getName() + " : " + RedisUtils.get("user_0001"));
//        });
    }

}
