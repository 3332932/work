package com.cn.springboot.controller;

import com.cn.springboot.model.Role;
import com.cn.springboot.service.RoleService;
import com.cn.springboot.base.BaseFilter;
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
import com.cn.springboot.util.PageUtil;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/rest/role/")
public class RoleRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(RoleRestController.class);
	@Autowired
	private RoleService roleServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addRole(@RequestBody Role obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("addRole {}",obj);
			roleServiceImpl.insertRole(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("addRole异常 {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getRoleById(@RequestBody Map param) {
		 Map<String,Object> map = new HashMap<>();
		try {
			Long id =(Long)param.get("id");
			log.info("getRoleById  {}",id);
			Role role = roleServiceImpl.selectRoleById(id);
			map.put("state",0);
			map.put("msg","success");
			map.put("data",role);
		} catch(Exception e) {
			log.error("getRole异常 {}",map,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateRoleById(@RequestBody Role obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateRoleById  {}",obj);
			roleServiceImpl.updateRoleById(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("updateRoleById  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findRoleList(@RequestBody Role  obj, HttpServletRequest request) {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("findRoleList  {}",obj);
			PageUtil.doPage(request);
			Page<Role> list = (Page<Role>)roleServiceImpl.findRoleList(obj);
			Paginator paginator=list.getPaginator();
			map.put("state",0);
			map.put("msg","success");
			map.put("data",list);
			map.put("paginator",paginator);
		} catch(Exception e) {
			log.error("findRoleList  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}
}
