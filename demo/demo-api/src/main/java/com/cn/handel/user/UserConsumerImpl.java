package com.cn.handel.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cn.facade.user.UserProvider;
import com.cn.model.User;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/7/24.
 */
@Component
public class UserConsumerImpl implements UserConsumer {
	
	@Autowired
	private UserProvider userProvider;
	
	
	
	@Override
	public User getUserByUserName(String userName){
		return userProvider.getUserByUserName(userName);
	}
	
	
	
	/**
	 * 分页
	 * @param currPage
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@Override
	public Map getUserPage(int currPage, int pageSize, User user){
		Map map = new HashMap<>();
		map.put("currPage",currPage);
		map.put("pageSize",pageSize);
		Map resMap= new HashMap();
		resMap.put("state",0);
		resMap.put("msg","success");
		
		return resMap;
	}
	
	/**
	 * 分页
	 * @param currPage
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@Override
	public Map getUserPage1(int currPage, int pageSize, User user){
		Map map = new HashMap<>();
		map.put("currPage",currPage);
		map.put("pageSize",pageSize);
		Map page = userProvider.getUserPage(currPage,pageSize,user);
		return page;
	}
	
	
}
