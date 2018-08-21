package com.cn.springboot;

import com.cn.springboot.controller.DemoHandle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootServiceApplicationTests {
    @Autowired
    DemoHandle demoHandle;
    private ExecutorService executorService = Executors.newFixedThreadPool(20);
    @Autowired
    JedisPool jedisPool;

    @Test
    public void contextLoads() {
        executorService.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                demoHandle.get();
            }
        }));
        executorService.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                demoHandle.get();
            }
        }));
        executorService.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                demoHandle.get();
            }
        }));
        executorService.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                demoHandle.get();
            }
        }));
        executorService.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                demoHandle.get();
            }
        }));
        executorService.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                demoHandle.get();
            }
        }));
        executorService.submit(new Thread(new Runnable() {
            @Override
            public void run() {
                demoHandle.get();
            }
        }));


        System.out.println(jedisPool);

    }

}
