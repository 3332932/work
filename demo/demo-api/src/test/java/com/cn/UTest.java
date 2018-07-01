package com.cn;

import com.alibaba.fastjson.JSONArray;
import com.cn.facade.user.UserProvider;
import com.cn.handel.user.UserConsumer;
import com.cn.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by 'ms.x' on 2017/7/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/demo-context.xml"})
public class UTest {
	@Autowired
	private UserConsumer userConsumer;
	
	
	private UserProvider userProvider;
	
	@Test
	public void test() throws Exception {
		User user = new User();
		Map map = userProvider.getUserPage(1,1,user);
		System.out.println(JSONArray.toJSONString(map));
	}
	@Test
	public void getUserPage1() throws Exception {
		User user = new User();
		Map map = userConsumer.getUserPage1(1,1,user);
		System.out.println(JSONArray.toJSONString(map));
	}
	
	public double getDoubleUp(double value){
		String val = String.valueOf(value);
		int length;
		if (val.indexOf(".")==-1) {
			length = val.length();
		}else {
			length=val.indexOf(".")+1;
		}
		int intVal = Integer.valueOf(val.substring(0,0));
		return intVal*Math.pow(10, length-1);
	}
	@Test
	public void getDoubleUpTest(){
		double df= getDoubleUp(2100);
		System.out.println(df);
	}
	
}
