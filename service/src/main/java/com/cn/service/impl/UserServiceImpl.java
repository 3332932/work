package com.cn.service.impl;

import com.cn.model.User;
import com.cn.dao.UserMapper;
import com.cn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertUser(User user) throws Exception {
		try {
			log.info("insert {}",user);
			userMapper.insertUser(user);
		} catch(Exception e) {
			log.error("insertUser异常 {}",user,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public User selectUserById(Long id) {
		log.info("selectUserById  {}",id);
		return userMapper.selectUserById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteUserById(Long id)throws Exception {
		try {
			log.info("deleteUserById  {}",id);
			int result = userMapper.deleteUserById(id);
			if (result < 1) {
				throw new Exception("deleteUserById失败");
			}
		} catch(Exception e) {
			log.error("deleteUserById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateUserById(User user) throws Exception {
		try {
			log.info("updateUserById  {}",user);
			int result = userMapper.updateUserById(user);
			if (result < 1) {
				throw new Exception("updateUserById失败");
			}
		} catch(Exception e) {
			log.error("updateUserById  {}",user,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<User> findUserList(User user) throws Exception {
		try {
			log.info("findUserList  {}",user);
			return userMapper.findUserList(user);
		} catch(Exception e) {
			log.error("findUserList  {}",user,e);
			throw e;
		}
	}
}
