package com.cn.controller;

import com.cn.model.RolePermission;
import com.cn.service.RolePermissionService;
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
 * 角色与权限关联表
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/rolePermission/")
public class RolePermissionController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(RolePermissionController.class);
	@Autowired
	private RolePermissionService rolePermissionServiceImpl;

	@RequestMapping("index")
	public String rolePermissionIndex(RolePermission rolePermission,Model model) throws Exception {
		return "rolePermission/rolePermissionIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddRolePermission(RolePermission rolePermission,Model model) throws Exception {
		return "rolePermission/addRolePermission";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addRolePermission(RolePermission rolePermission) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addRolePermission {}",rolePermission);
			rolePermissionServiceImpl.insertRolePermission(rolePermission);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addRolePermission异常 {}",rolePermission,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_rolePermission")
	public String getRolePermissionById(Long id,Model model) {
		log.info("getRolePermissionById  {}",id);
		RolePermission result = rolePermissionServiceImpl.selectRolePermissionById(id);
		model.addAttribute("item",result);
		return "rolePermission/updateRolePermission";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteRolePermissionById(Long id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteRolePermissionById  {}",id);
			rolePermissionServiceImpl.deleteRolePermissionById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteRolePermissionById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateRolePermission(Long id,Model model) throws Exception {
		RolePermission result = rolePermissionServiceImpl.selectRolePermissionById(id);
		model.addAttribute("item",result);
		return "rolePermission/updateRolePermission";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateRolePermissionById(RolePermission rolePermission) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateRolePermissionById  {}",rolePermission);
			rolePermissionServiceImpl.updateRolePermissionById(rolePermission);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateRolePermissionById  {}",rolePermission,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findRolePermissionList(RolePermission rolePermission,Model model, HttpServletRequest request) {
		try {
			log.info("findRolePermissionList  {}",rolePermission);
			PageUtil.doPage(request);
			Page<RolePermission> result = (Page<RolePermission>)rolePermissionServiceImpl.findRolePermissionList(rolePermission);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findRolePermissionList  {}",rolePermission,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "rolePermission/rolePermissionList";
	}
}
