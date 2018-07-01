package com.cn.controller;

import com.cn.model.User;
import com.cn.service.UserService;
import com.cn.base.BaseFilter;
import org.slf4j.Logger;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.List;
import com.cn.util.PageUtil;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
/**
 * 用户表
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/user/")
public class UserController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userServiceImpl;

	@RequestMapping("index")
	public String userIndex(User user,Model model) throws Exception {
		return "user/userIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddUser(User user,Model model) throws Exception {
		return "user/addUser";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addUser(User user) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addUser {}",user);
			userServiceImpl.insertUser(user);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addUser异常 {}",user,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_user")
	public String getUserById(Long id,Model model) {
		log.info("getUserById  {}",id);
		User result = userServiceImpl.selectUserById(id);
		model.addAttribute("item",result);
		return "user/updateUser";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteUserById(Long id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteUserById  {}",id);
			userServiceImpl.deleteUserById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteUserById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateUser(Long id,Model model) throws Exception {
		User result = userServiceImpl.selectUserById(id);
		model.addAttribute("item",result);
		return "user/updateUser";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateUserById(User user) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateUserById  {}",user);
			userServiceImpl.updateUserById(user);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateUserById  {}",user,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findUserList(User user,Model model, HttpServletRequest request) {
		try {
			log.info("findUserList  {}",user);
			PageUtil.doPage(request);
			Page<User> result = (Page<User>)userServiceImpl.findUserList(user);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findUserList  {}",user,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "user/userList";
	}
}
