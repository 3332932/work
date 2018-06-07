package com.cn.service.user;

import com.cn.model.User;
import com.github.pagehelper.Page;

/**
 * Created by 'ms.x' on 2017/7/24.
 */
public interface UserService {
	User getUserByUserName(String userName);
	
	Page getUserPage(int currPage, int pageSize, User user);
	
	int updateByUserId(User user);
}
