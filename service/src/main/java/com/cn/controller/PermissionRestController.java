package com.cn.controller;

import com.cn.model.Permission;
import com.cn.service.PermissionService;
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
@RequestMapping("/rest/permission/")
public class PermissionRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(PermissionRestController.class);
	@Autowired
	private PermissionService permissionServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addPermission(@RequestBody Permission obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("addPermission {}",obj);
			permissionServiceImpl.insertPermission(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("addPermission异常 {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getPermissionById(@RequestBody Map param) {
		 Map<String,Object> map = new HashMap<>();
		try {
			Long id =(Long)param.get("id");
			log.info("getPermissionById  {}",id);
			Permission permission = permissionServiceImpl.selectPermissionById(id);
			map.put("state",0);
			map.put("msg","success");
			map.put("data",permission);
		} catch(Exception e) {
			log.error("getPermission异常 {}",map,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updatePermissionById(@RequestBody Permission obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("updatePermissionById  {}",obj);
			permissionServiceImpl.updatePermissionById(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("updatePermissionById  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findPermissionList(@RequestBody Permission  obj, HttpServletRequest request) {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("findPermissionList  {}",obj);
			PageUtil.doPage(request);
			Page<Permission> list = (Page<Permission>)permissionServiceImpl.findPermissionList(obj);
			Paginator paginator=list.getPaginator();
			map.put("state",0);
			map.put("msg","success");
			map.put("data",list);
			map.put("paginator",paginator);
		} catch(Exception e) {
			log.error("findPermissionList  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}
}
