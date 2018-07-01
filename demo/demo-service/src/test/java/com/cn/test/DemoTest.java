package com.cn.test;

import com.alibaba.fastjson.JSONArray;
import com.cn.model.User;
import com.cn.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 'ms.x' on 2017/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-service-content.xml")
public class DemoTest {
	@Autowired
	private UserService userServiceImpl;

	@Test
	public void daotest1(){
		User result = userServiceImpl.getUserByUserName("jacky");
		System.out.println(JSONArray.toJSONString(result.getNickName()));
		System.out.println(result.getUserName());
		System.out.println(JSONArray.toJSONString(result.getUserId()));
	}
	
}
