package com.cn.springboot.service.Impl;

import com.cn.springboot.model.Role;
import com.cn.springboot.dao.RoleMapper;
import com.cn.springboot.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

	private Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertRole(Role role) throws Exception {
		try {
			log.info("insert {}",role);
			roleMapper.insertRole(role);
		} catch(Exception e) {
			log.error("insertRole异常 {}",role,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Role selectRoleById(Long id) {
		log.info("selectRoleById  {}",id);
		return roleMapper.selectRoleById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteRoleById(Long id)throws Exception {
		try {
			log.info("deleteRoleById  {}",id);
			int result = roleMapper.deleteRoleById(id);
			if (result < 1) {
				throw new Exception("deleteRoleById失败");
			}
		} catch(Exception e) {
			log.error("deleteRoleById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateRoleById(Role role) throws Exception {
		try {
			log.info("updateRoleById  {}",role);
			int result = roleMapper.updateRoleById(role);
			if (result < 1) {
				throw new Exception("updateRoleById失败");
			}
		} catch(Exception e) {
			log.error("updateRoleById  {}",role,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Role> findRoleList(Role role) throws Exception {
		try {
			log.info("findRoleList  {}",role);
			return roleMapper.findRoleList(role);
		} catch(Exception e) {
			log.error("findRoleList  {}",role,e);
			throw e;
		}
	}
}
