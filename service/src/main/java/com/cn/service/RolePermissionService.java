package com.cn.service;

import com.cn.model.RolePermission;
import java.util.List;
public interface RolePermissionService {

	/**
	 * 主键查询
	 **/
	RolePermission selectRolePermissionById(Long id);

	/**
	 * 主键删除
	 **/
	void deleteRolePermissionById(Long id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateRolePermissionById(RolePermission rolePermission) throws Exception;

	/**
	 * 插入
	 **/
	void insertRolePermission(RolePermission rolePermission) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<RolePermission> findRolePermissionList(RolePermission rolePermission) throws Exception;

}
