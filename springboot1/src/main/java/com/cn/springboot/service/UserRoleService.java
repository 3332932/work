package com.cn.springboot.service;

import com.cn.springboot.model.UserRole;
import java.util.List;
public interface UserRoleService {

	/**
	 * 主键查询
	 **/
	UserRole selectUserRoleById(Long id);

	/**
	 * 主键删除
	 **/
	void deleteUserRoleById(Long id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateUserRoleById(UserRole userRole) throws Exception;

	/**
	 * 插入
	 **/
	void insertUserRole(UserRole userRole) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<UserRole> findUserRoleList(UserRole userRole) throws Exception;

}
