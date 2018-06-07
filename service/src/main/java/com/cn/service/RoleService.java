package com.cn.service;

import com.cn.model.Role;
import java.util.List;
public interface RoleService {

	/**
	 * 主键查询
	 **/
	Role selectRoleById(Long id);

	/**
	 * 主键删除
	 **/
	void deleteRoleById(Long id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateRoleById(Role role) throws Exception;

	/**
	 * 插入
	 **/
	void insertRole(Role role) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Role> findRoleList(Role role) throws Exception;

}
