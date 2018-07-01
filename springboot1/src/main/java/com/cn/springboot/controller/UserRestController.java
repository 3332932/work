package com.cn.springboot.controller;

import com.cn.springboot.model.User;
import com.cn.springboot.service.UserService;
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
@RequestMapping("/rest/user/")
public class UserRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(UserRestController.class);
	@Autowired
	private UserService userServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addUser(@RequestBody User obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("addUser {}",obj);
			userServiceImpl.insertUser(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("addUser异常 {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getUserById(@RequestBody Map param) {
		 Map<String,Object> map = new HashMap<>();
		try {
			Long id =(Long)param.get("id");
			log.info("getUserById  {}",id);
			User user = userServiceImpl.selectUserById(id);
			map.put("state",0);
			map.put("msg","success");
			map.put("data",user);
		} catch(Exception e) {
			log.error("getUser异常 {}",map,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateUserById(@RequestBody User obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateUserById  {}",obj);
			userServiceImpl.updateUserById(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("updateUserById  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findUserList(@RequestBody User  obj, HttpServletRequest request) {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("findUserList  {}",obj);
			PageUtil.doPage(request);
			Page<User> list = (Page<User>)userServiceImpl.findUserList(obj);
			Paginator paginator=list.getPaginator();
			map.put("state",0);
			map.put("msg","success");
			map.put("data",list);
			map.put("paginator",paginator);
		} catch(Exception e) {
			log.error("findUserList  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}
}
