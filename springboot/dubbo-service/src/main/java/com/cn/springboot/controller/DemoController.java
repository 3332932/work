package com.cn.springboot.controller;

import com.alibaba.dubbo.config.annotation.Service;
import com.cn.facade.user.UserProvider;
import com.cn.model.User;
import com.cn.springboot.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.TimeoutUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class DemoController implements UserProvider {

    @Autowired
    private RedisService redisService;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public User getUserByUserName(String userName) {
        User user =new User();
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set("key","value","NX","PX",1000);
        if (StringUtils.isNotEmpty(result)){
            user.setNickName("获取到锁");

        }else {
            user.setNickName("没有获取到锁");
        }
        System.out.printf("");
        redisService.test();

        user.setCreatedTime(new Date());
        user.setUserId(1L);
        user.setUserName("user");
        return user;
    }


    @Override
    public Map getUserPage(int currPage, int pageSize, User user) {
        return null;
    }

    @Override
    public int updateByUserId(User user) {
        return 0;
    }
}
