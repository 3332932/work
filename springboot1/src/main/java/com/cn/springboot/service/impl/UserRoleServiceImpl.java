package com.cn.springboot.service.impl;

import com.cn.springboot.model.UserRole;
import com.cn.springboot.dao.UserRoleMapper;
import com.cn.springboot.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {

	private Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	@Autowired
	private UserRoleMapper userRoleMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertUserRole(UserRole userRole) throws Exception {
		try {
			log.info("insert {}",userRole);
			userRoleMapper.insertUserRole(userRole);
		} catch(Exception e) {
			log.error("insertUserRole异常 {}",userRole,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public UserRole selectUserRoleById(Long id) {
		log.info("selectUserRoleById  {}",id);
		return userRoleMapper.selectUserRoleById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteUserRoleById(Long id)throws Exception {
		try {
			log.info("deleteUserRoleById  {}",id);
			int result = userRoleMapper.deleteUserRoleById(id);
			if (result < 1) {
				throw new Exception("deleteUserRoleById失败");
			}
		} catch(Exception e) {
			log.error("deleteUserRoleById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateUserRoleById(UserRole userRole) throws Exception {
		try {
			log.info("updateUserRoleById  {}",userRole);
			int result = userRoleMapper.updateUserRoleById(userRole);
			if (result < 1) {
				throw new Exception("updateUserRoleById失败");
			}
		} catch(Exception e) {
			log.error("updateUserRoleById  {}",userRole,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<UserRole> findUserRoleList(UserRole userRole) throws Exception {
		try {
			log.info("findUserRoleList  {}",userRole);
			return userRoleMapper.findUserRoleList(userRole);
		} catch(Exception e) {
			log.error("findUserRoleList  {}",userRole,e);
			throw e;
		}
	}
}
