package com.cn.facade.User;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.cn.facade.user.UserProvider;
import com.cn.model.User;
import com.cn.service.user.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/7/24.
 */
@Service
public class UserProviderImpl implements UserProvider {
	
	@Autowired
	private UserService userServiceImpl;
	
	
	@Override
	public User getUserByUserName(String userName) {
		
		return userServiceImpl.getUserByUserName(userName);
	}

	@Override
	public Map getUserPage(int currPage,int pageSize, User user) {
		Page page = userServiceImpl.getUserPage(currPage,pageSize,user);
		Map resMap= new HashMap();
		resMap.put("totalCount",page.getTotal());
		resMap.put("item",page.getResult());
		resMap.put("state",0);
		resMap.put("msg","success");
		return resMap;
	}
	
	
	
	@Override
	public int updateByUserId(User user) {
		return userServiceImpl.updateByUserId(user);
	}
}
