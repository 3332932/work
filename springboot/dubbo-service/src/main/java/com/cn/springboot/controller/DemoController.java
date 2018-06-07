package com.cn.springboot.controller;

import com.alibaba.dubbo.config.annotation.Service;
import com.cn.facade.user.UserProvider;
import com.cn.model.User;

import java.util.Date;
import java.util.Map;

@Service
public class DemoController implements UserProvider {


    @Override
    public User getUserByUserName(String userName) {
        User user =new User();
        user.setCreatedTime(new Date());
        user.setNickName("sdfdsfd");
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
