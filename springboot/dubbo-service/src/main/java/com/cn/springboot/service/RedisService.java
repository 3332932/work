package com.cn.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JedisPool jedisPool;

    public void test() {
        redisTemplate.opsForHash().put("key", "w", "dfdfdf");
        Object value = redisTemplate.opsForHash().get("key", "w");

        System.out.println(value);
    }

    public boolean setnx(String key, String value,Long timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value, "NX", "PX", timeout);
            return "OK".equals(result);

        } catch (Exception e) {
            logger.error("getUserByUserName", e);
        } finally {

            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }
    public boolean setnx(Jedis jedis,String key, String value,Long timeout) {
        try {
            String result = jedis.set(key, value, "NX", "PX", timeout);
            return "OK".equals(result);

        } catch (Exception e) {
            logger.error("getUserByUserName", e);
        }
        return false;
    }

}
