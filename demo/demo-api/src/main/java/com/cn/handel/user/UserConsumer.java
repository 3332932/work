package com.cn.handel.user;

import com.cn.model.User;

import java.util.Map;

/**
 * Created by 'ms.x' on 2017/7/24.
 */
public interface UserConsumer {
	User getUserByUserName(String userName);
	
	Map getUserPage(int currPage, int pageSize, User user);
	
	Map getUserPage1(int currPage, int pageSize, User user);
}
