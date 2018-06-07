package com.cn.controller;

import com.cn.model.RolePermission;
import com.cn.service.RolePermissionService;
import com.cn.base.BaseFilter;
import org.slf4j.Logger;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.cn.util.PageUtil;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/rest/rolePermission/")
public class RolePermissionRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(RolePermissionRestController.class);
	@Autowired
	private RolePermissionService rolePermissionServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addRolePermission(@RequestBody RolePermission obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("addRolePermission {}",obj);
			rolePermissionServiceImpl.insertRolePermission(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("addRolePermission异常 {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getRolePermissionById(@RequestBody Map param) {
		 Map<String,Object> map = new HashMap<>();
		try {
			Long id =(Long)param.get("id");
			log.info("getRolePermissionById  {}",id);
			RolePermission rolePermission = rolePermissionServiceImpl.selectRolePermissionById(id);
			map.put("state",0);
			map.put("msg","success");
			map.put("data",rolePermission);
		} catch(Exception e) {
			log.error("getRolePermission异常 {}",map,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateRolePermissionById(@RequestBody RolePermission obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateRolePermissionById  {}",obj);
			rolePermissionServiceImpl.updateRolePermissionById(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("updateRolePermissionById  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findRolePermissionList(@RequestBody RolePermission  obj, HttpServletRequest request) {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("findRolePermissionList  {}",obj);
			PageUtil.doPage(request);
			Page<RolePermission> list = (Page<RolePermission>)rolePermissionServiceImpl.findRolePermissionList(obj);
			Paginator paginator=list.getPaginator();
			map.put("state",0);
			map.put("msg","success");
			map.put("data",list);
			map.put("paginator",paginator);
		} catch(Exception e) {
			log.error("findRolePermissionList  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}
}
