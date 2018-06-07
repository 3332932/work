package com.cn.service;

import com.cn.model.Permission;
import java.util.List;
public interface PermissionService {

	/**
	 * 主键查询
	 **/
	Permission selectPermissionById(Long id);

	/**
	 * 主键删除
	 **/
	void deletePermissionById(Long id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updatePermissionById(Permission permission) throws Exception;

	/**
	 * 插入
	 **/
	void insertPermission(Permission permission) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Permission> findPermissionList(Permission permission) throws Exception;

}
