package com.cn.service.user;

import com.cn.dao.UserMapper;
import com.cn.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 'ms.x' on 2017/7/24.
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUserName(String userName) {
		return userMapper.selectByUserName(userName);
	}
	
	@Override
	public Page getUserPage(int currPage, int pageSize, User user) {
		PageHelper.startPage(currPage,pageSize);
		return (Page) userMapper.getUserPage(user);
	}
	
	@Override
	public int updateByUserId(User user) {
		return userMapper.updateByPrimaryKey(user);
	}
}
