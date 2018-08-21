package com.cn.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cn.facade.demo.Demo;
import com.cn.facade.user.UserProvider;
import com.cn.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController implements UserProvider,Demo {
    @Reference
    private UserProvider userProvider;
    @Reference
    private Demo demo;
    @RequestMapping("/")
    public Object dsfe(){
        User user = getUserByUserName("user");
        Map map = new HashMap<>();
        map.put("state",0);
        map.put("msg", "success");
        map.put("data",user);
        return map;
    }

    @RequestMapping("/test")
    public Object test(String s){
        Object obj = cacheable(s);
        Map map = new HashMap<>();
        map.put("state",0);
        map.put("msg", "success");
        map.put("data",obj);
        return map;
    }
    @RequestMapping("/evict")
    public Object cacheEvict(String s){
        Object obj = CacheEvict(s);
        Map map = new HashMap<>();
        map.put("state",0);
        map.put("msg", "success");
        map.put("data",obj);
        return map;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userProvider.getUserByUserName(userName);
    }

    @Override
    public Map getUserPage(int currPage, int pageSize, User user) {
        return null;
    }

    @Override
    public int updateByUserId(User user) {
        return 0;
    }

    @Override
    public Object get() {
        return demo.get();
    }

    @Override
    public Object cacheable(String s) {
        return demo.cacheable(s);
    }

    @Override
    public Object CacheEvict(String s) {
        return demo.CacheEvict(s);
    }
}
