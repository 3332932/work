package com.cn.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void test(){
        redisTemplate.opsForHash().put("key","w","dfdfdf");
        Object value = redisTemplate.opsForHash().get("key","w");

        System.out.println(value);
    }
}
