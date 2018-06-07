package com.cn.service.impl;

import com.cn.model.Permission;
import com.cn.dao.PermissionMapper;
import com.cn.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {

	private Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
	@Autowired
	private PermissionMapper permissionMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertPermission(Permission permission) throws Exception {
		try {
			log.info("insert {}",permission);
			permissionMapper.insertPermission(permission);
		} catch(Exception e) {
			log.error("insertPermission异常 {}",permission,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Permission selectPermissionById(Long id) {
		log.info("selectPermissionById  {}",id);
		return permissionMapper.selectPermissionById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deletePermissionById(Long id)throws Exception {
		try {
			log.info("deletePermissionById  {}",id);
			int result = permissionMapper.deletePermissionById(id);
			if (result < 1) {
				throw new Exception("deletePermissionById失败");
			}
		} catch(Exception e) {
			log.error("deletePermissionById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updatePermissionById(Permission permission) throws Exception {
		try {
			log.info("updatePermissionById  {}",permission);
			int result = permissionMapper.updatePermissionById(permission);
			if (result < 1) {
				throw new Exception("updatePermissionById失败");
			}
		} catch(Exception e) {
			log.error("updatePermissionById  {}",permission,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Permission> findPermissionList(Permission permission) throws Exception {
		try {
			log.info("findPermissionList  {}",permission);
			return permissionMapper.findPermissionList(permission);
		} catch(Exception e) {
			log.error("findPermissionList  {}",permission,e);
			throw e;
		}
	}
}
