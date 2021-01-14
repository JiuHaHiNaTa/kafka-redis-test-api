package com.example.redisapitest.service;

import com.example.redisapitest.entity.User;
import com.example.redisapitest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImp {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate<String, User> redisTemplate;

    public User findUserById(String id) {
        String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        return Optional.ofNullable(operations.get(key))
                .orElseGet(() -> {
                    User user = userMapper.findUserById(key);
                    Optional.ofNullable(user).ifPresent(temp -> {
                        operations.set(key, user, 5, TimeUnit.HOURS);
                    });
                    return user;
                });

    }

}
