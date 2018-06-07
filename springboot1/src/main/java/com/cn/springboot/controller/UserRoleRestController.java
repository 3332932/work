package com.cn.springboot.controller;

import com.cn.springboot.model.UserRole;
import com.cn.springboot.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SuppressWarnings("all")
@RequestMapping("/userRole/")
public class UserRoleRestController {

	private Logger log = LoggerFactory.getLogger(UserRoleRestController.class);
	@Autowired
	private UserRoleService userRoleServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addUserRole(@RequestBody UserRole obj) throws Exception {
		try {
			log.info("addUserRole {}",obj);
			userRoleServiceImpl.insertUserRole(obj);
		} catch(Exception e) {
			log.error("addUserRole异常 {}",obj,e);
		}
		return 0;
	}

}
