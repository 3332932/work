package com.cn.jwt.controller;

import com.cn.jwt.service.DemoService;
import com.cn.jwt.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DemoHandle{
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private DemoService demoService;
    @Autowired
    private RedisService redisService;
    private ExecutorService executorService1 = Executors.newFixedThreadPool(100);
    private static Logger logger = LoggerFactory.getLogger(DemoHandle.class);

    public Object get(){
        getall();
        return "";

    }

    @Cacheable(value="cache")
    public Object cacheable(String s) {

        return demoService.getFromDataBase();
    }

    @CacheEvict(value="cache")
    public Object CacheEvict(String s) {
        return demoService.margeFromDataBase();
    }

    public Object tes(){
        Jedis jedis =null;
        String value =null;
        try {
            jedis = jedisPool.getResource();
            //System.out.println("redis缓存穿透测试=============================");
            value= sdfe(jedis);
            logger.info("tes: value = {}",value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis !=null){
                jedis.close();
            }
        }

        return value;
    }

    public String sdfe(Jedis jedis) throws InterruptedException {
        //从缓存中获取值
        String value = jedis.get("key");
        logger.info("  正在访问");
        if (value == null){
            //缓存失效后在redis里加一个访问锁，第一个访问者直接去数据库里拿，其它访问者等待
            logger.info(" 尝试穿透");
            if(redisService.setnx(jedis,"key123","1",2000L)){
                logger.info("  已穿透");
                //第一个访问者从数据库里拿到数据并把数据放入内存中
                value = demoService.getFromDataBase();
                jedis.set("key",value);
                jedis.expire("key",1);
                //删除锁
                jedis.del("key123");
            }else{
                //没有获取到锁的，等待时间，重新从内存中获取值
                Thread.sleep(100);
                value= sdfe(jedis);
            }
        }
        logger.info("  返回结果");
        return value;

    }





    public void getall(){
        for (int i=0;i<100;i++){
            executorService1.submit(new Runnable() {
                @Override
                public void run() {
                    tes();
                }
            });
        }


    }
}
