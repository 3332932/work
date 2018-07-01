package com.cn.facade.user;

import com.cn.model.User;
import com.github.pagehelper.Page;

import java.util.Map;

/**
 * Created by 'ms.x' on 2017/7/24.
 */
public interface UserProvider {
	/**
	 * 用户名获取用户信息
	 * @param userName
	 * @return
	 */
	 User getUserByUserName(String userName);
	

	/**
	 * 用户分页
	 * @param currPage 当前页
	 * @param pageSize 每页数
	 * @param user 参数
	 * @return
	 */
	Map getUserPage(int currPage,int pageSize, User user);
		
		
		/**
		 * 更新用户信息
		 * @param user
		 * @return 0失败，1成功
		 */
	 int updateByUserId(User user);
	
}
