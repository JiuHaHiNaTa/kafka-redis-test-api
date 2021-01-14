package com.example.redisapitest.utils;

import com.example.redisapitest.config.properties.RedisProperties;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Optional;

/**
 * redis相关操作工具类
 */
public class RedisUtils {

    private static final JedisPool jedisPool;

    static {
        String ip = RedisProperties.getHost();
        int port = Integer.parseInt(RedisProperties.getPort());
        String password = RedisProperties.getPassword();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(RedisProperties.getMaxActive());
        jedisPoolConfig.setMaxIdle(RedisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(RedisProperties.getMaxWaitTime());
        jedisPool = Optional.ofNullable(password)
                .map(passwd -> new JedisPool(jedisPoolConfig, ip, port, 10000, passwd))
                .orElse(new JedisPool(jedisPoolConfig, ip, port, 10000));
    }

    public static String get(String key){
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

}
