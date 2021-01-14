package com.example.redisapitest.service;

import com.alibaba.fastjson.JSONObject;
import com.example.redisapitest.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

@Slf4j
@Service
public class KafkaProducerServiceImp {

    private final String TOPIC_TEST = "test-topic-01";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendToKafka(Object object) {
        String jsonStr = JSONObject.toJSONString(object);
        log.info("准备发送消息为：{}", object);
        //发送消息
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_TEST, object.toString(), jsonStr);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                //成功的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringStringSendResult.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
            }
        });

    }
}
