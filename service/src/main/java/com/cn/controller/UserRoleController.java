package com.cn.controller;

import com.cn.model.UserRole;
import com.cn.service.UserRoleService;
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
 * 用户与角色关联表
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/userRole/")
public class UserRoleController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(UserRoleController.class);
	@Autowired
	private UserRoleService userRoleServiceImpl;

	@RequestMapping("index")
	public String userRoleIndex(UserRole userRole,Model model) throws Exception {
		return "userRole/userRoleIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddUserRole(UserRole userRole,Model model) throws Exception {
		return "userRole/addUserRole";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addUserRole(UserRole userRole) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addUserRole {}",userRole);
			userRoleServiceImpl.insertUserRole(userRole);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addUserRole异常 {}",userRole,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_userRole")
	public String getUserRoleById(Long id,Model model) {
		log.info("getUserRoleById  {}",id);
		UserRole result = userRoleServiceImpl.selectUserRoleById(id);
		model.addAttribute("item",result);
		return "userRole/updateUserRole";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteUserRoleById(Long id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteUserRoleById  {}",id);
			userRoleServiceImpl.deleteUserRoleById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteUserRoleById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateUserRole(Long id,Model model) throws Exception {
		UserRole result = userRoleServiceImpl.selectUserRoleById(id);
		model.addAttribute("item",result);
		return "userRole/updateUserRole";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateUserRoleById(UserRole userRole) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateUserRoleById  {}",userRole);
			userRoleServiceImpl.updateUserRoleById(userRole);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateUserRoleById  {}",userRole,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findUserRoleList(UserRole userRole,Model model, HttpServletRequest request) {
		try {
			log.info("findUserRoleList  {}",userRole);
			PageUtil.doPage(request);
			Page<UserRole> result = (Page<UserRole>)userRoleServiceImpl.findUserRoleList(userRole);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findUserRoleList  {}",userRole,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "userRole/userRoleList";
	}
}
