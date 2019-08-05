package com.cn.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cn.facade.demo.Demo;
import com.cn.facade.user.UserProvider;
import com.cn.model.User;
import com.cn.springboot.model.DUser;
import com.cn.springboot.model.Pay;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Api("swaggerDemoController相关的api")
@RestController
public class DemoController implements UserProvider,Demo {
    @Reference
    private UserProvider userProvider;
    @Reference
    private Demo demo;

     @RequestMapping(value="/dsfe",method = RequestMethod.POST)
    public Object dsfe(@RequestBody Pay pay){
        User user = getUserByUserName(pay.getUserName());
        Map map = new HashMap<>();
        map.put("state",0);
        map.put("msg", "success");
        map.put("data",user);
        return map;
    }
    @ApiOperation(value = "cacheable redis缓存", notes = "缓存雪崩")
    @RequestMapping(value = "/test",method= RequestMethod.POST)
    public Object test(@RequestBody DUser user){
         String name = user.getName();
        Object obj = cacheable(name);
        Map map = new HashMap<>();
        map.put("state",0);
        map.put("msg", "success");
        map.put("data",obj);
        return map;
    }
    @RequestMapping(value = "/evict",method = RequestMethod.POST)
    public Object cacheEvict1(String s){
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
