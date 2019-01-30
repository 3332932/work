package com.cn.jwt.controller;

import com.cn.jwt.entity.User;
import com.cn.jwt.service.DemoService;
import com.cn.jwt.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DemoController {
    private static Logger logger=LoggerFactory.getLogger(DemoController.class);
    private static ExecutorService executorService= Executors.newFixedThreadPool(10);


    @Autowired
    private RedisService redisService;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private DemoService demoService;





    public User getUserByUserName(String userName) {
        Sdsfe sdsfe=new Sdsfe("key","value");
        Sdsfe sdsfe1=new Sdsfe("key","valu");
        Sdsfe sdsfe2=new Sdsfe("key","va");
        Sdsfe sdsfe3=new Sdsfe("key","v");

        executorService.submit(sdsfe);
        executorService.submit(sdsfe1);
        executorService.submit(sdsfe2);
        executorService.submit(sdsfe3);
        User user =new User();
        try {

            user.setCreateTime(new Date());
            user.setUserId(1L);
            user.setUserName("user");

        } catch (Exception e) {
            logger.error("getUserByUserName",e);
            user.setCreateTime(new Date());
            user.setUserId(1L);
            user.setUserName("user");
            user.setNickName(e.getMessage());

        }finally {

        }
        return user;
    }


    public Map getUserPage(int currPage, int pageSize, User user) {
        return null;
    }

    public int updateByUserId(User user) {
        return 0;
    }

    public Boolean redisLockDemo(String s,String value) {

        return true;

    }

    public Boolean dfsdf(String s, String value){
        User user =new User();
        Jedis jedis =null;
        Boolean flag=false;
        String value1=value+System.currentTimeMillis();
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(s,value1,"NX","PX",4000);
            if (StringUtils.isNotEmpty(result)){
                logger.info("执行了。。。。。。");
                flag=true;

            }else {
                logger.info("未执行。。。。。。");
            }


        } catch (Exception e) {
            logger.error("getUserByUserName",e);
        }finally {

            if (jedis!=null){
                releaseRedisKey(jedis,s,value1);
            }
        }
        return flag;
    }


    public boolean releaseRedisKey(Jedis jedis,String lockName, String identifier){
        boolean retFlag = false;
        try {
            while (true) {
                // 监视lock，准备开始事务
                jedis.watch(lockName);
                // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                logger.info("======"+identifier.equals(jedis.get(lockName)));
                if (identifier.equals(jedis.get(lockName))) {
                    Transaction transaction = jedis.multi();
                    logger.info("删除成功=======================");
                    transaction.del(lockName);
                    List<Object> results = transaction.exec();
                    if (results == null) {
                        continue;
                    }
                    retFlag = true;
                }else{
                    logger.info("删除失败=======================");
                }
                jedis.unwatch();
                break;
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return retFlag;
    }

    private class Sdsfe implements Runnable{
        private String key;
        private String value;

        public Sdsfe(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public void run() {

            dfsdf(key,value);
        }
    }

}

