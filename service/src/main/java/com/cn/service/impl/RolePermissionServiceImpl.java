package com.cn.service.impl;

import com.cn.model.RolePermission;
import com.cn.dao.RolePermissionMapper;
import com.cn.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	private Logger log = LoggerFactory.getLogger(RolePermissionServiceImpl.class);
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertRolePermission(RolePermission rolePermission) throws Exception {
		try {
			log.info("insert {}",rolePermission);
			rolePermissionMapper.insertRolePermission(rolePermission);
		} catch(Exception e) {
			log.error("insertRolePermission异常 {}",rolePermission,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public RolePermission selectRolePermissionById(Long id) {
		log.info("selectRolePermissionById  {}",id);
		return rolePermissionMapper.selectRolePermissionById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteRolePermissionById(Long id)throws Exception {
		try {
			log.info("deleteRolePermissionById  {}",id);
			int result = rolePermissionMapper.deleteRolePermissionById(id);
			if (result < 1) {
				throw new Exception("deleteRolePermissionById失败");
			}
		} catch(Exception e) {
			log.error("deleteRolePermissionById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateRolePermissionById(RolePermission rolePermission) throws Exception {
		try {
			log.info("updateRolePermissionById  {}",rolePermission);
			int result = rolePermissionMapper.updateRolePermissionById(rolePermission);
			if (result < 1) {
				throw new Exception("updateRolePermissionById失败");
			}
		} catch(Exception e) {
			log.error("updateRolePermissionById  {}",rolePermission,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<RolePermission> findRolePermissionList(RolePermission rolePermission) throws Exception {
		try {
			log.info("findRolePermissionList  {}",rolePermission);
			return rolePermissionMapper.findRolePermissionList(rolePermission);
		} catch(Exception e) {
			log.error("findRolePermissionList  {}",rolePermission,e);
			throw e;
		}
	}
}
