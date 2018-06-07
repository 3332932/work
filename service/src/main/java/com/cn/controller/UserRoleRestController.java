package com.cn.controller;

import com.cn.model.UserRole;
import com.cn.service.UserRoleService;
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
@RequestMapping("/rest/userRole/")
public class UserRoleRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(UserRoleRestController.class);
	@Autowired
	private UserRoleService userRoleServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addUserRole(@RequestBody UserRole obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("addUserRole {}",obj);
			userRoleServiceImpl.insertUserRole(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("addUserRole异常 {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getUserRoleById(@RequestBody Map param) {
		 Map<String,Object> map = new HashMap<>();
		try {
			Long id =(Long)param.get("id");
			log.info("getUserRoleById  {}",id);
			UserRole userRole = userRoleServiceImpl.selectUserRoleById(id);
			map.put("state",0);
			map.put("msg","success");
			map.put("data",userRole);
		} catch(Exception e) {
			log.error("getUserRole异常 {}",map,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateUserRoleById(@RequestBody UserRole obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateUserRoleById  {}",obj);
			userRoleServiceImpl.updateUserRoleById(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("updateUserRoleById  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findUserRoleList(@RequestBody UserRole  obj, HttpServletRequest request) {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("findUserRoleList  {}",obj);
			PageUtil.doPage(request);
			Page<UserRole> list = (Page<UserRole>)userRoleServiceImpl.findUserRoleList(obj);
			Paginator paginator=list.getPaginator();
			map.put("state",0);
			map.put("msg","success");
			map.put("data",list);
			map.put("paginator",paginator);
		} catch(Exception e) {
			log.error("findUserRoleList  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}
}
