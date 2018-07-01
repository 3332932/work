package com.cn.springboot.service;

import com.cn.springboot.model.User;
import java.util.List;
public interface UserService {

	/**
	 * 主键查询
	 **/
	User selectUserById(Long id);

	/**
	 * 主键删除
	 **/
	void deleteUserById(Long id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateUserById(User user) throws Exception;

	/**
	 * 插入
	 **/
	void insertUser(User user) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<User> findUserList(User user) throws Exception;

}
