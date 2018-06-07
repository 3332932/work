package com.cn.controller;

import com.cn.model.Role;
import com.cn.service.RoleService;
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
 * 角色表
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/role/")
public class RoleController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleServiceImpl;

	@RequestMapping("index")
	public String roleIndex(Role role,Model model) throws Exception {
		return "role/roleIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddRole(Role role,Model model) throws Exception {
		return "role/addRole";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addRole(Role role) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addRole {}",role);
			roleServiceImpl.insertRole(role);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addRole异常 {}",role,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_role")
	public String getRoleById(Long id,Model model) {
		log.info("getRoleById  {}",id);
		Role result = roleServiceImpl.selectRoleById(id);
		model.addAttribute("item",result);
		return "role/updateRole";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteRoleById(Long id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteRoleById  {}",id);
			roleServiceImpl.deleteRoleById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteRoleById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateRole(Long id,Model model) throws Exception {
		Role result = roleServiceImpl.selectRoleById(id);
		model.addAttribute("item",result);
		return "role/updateRole";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateRoleById(Role role) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateRoleById  {}",role);
			roleServiceImpl.updateRoleById(role);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateRoleById  {}",role,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findRoleList(Role role,Model model, HttpServletRequest request) {
		try {
			log.info("findRoleList  {}",role);
			PageUtil.doPage(request);
			Page<Role> result = (Page<Role>)roleServiceImpl.findRoleList(role);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findRoleList  {}",role,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "role/roleList";
	}
}
