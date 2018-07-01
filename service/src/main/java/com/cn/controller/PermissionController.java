package com.cn.controller;

import com.cn.model.Permission;
import com.cn.service.PermissionService;
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
 * 权限表
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/permission/")
public class PermissionController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(PermissionController.class);
	@Autowired
	private PermissionService permissionServiceImpl;

	@RequestMapping("index")
	public String permissionIndex(Permission permission,Model model) throws Exception {
		return "permission/permissionIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddPermission(Permission permission,Model model) throws Exception {
		return "permission/addPermission";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addPermission(Permission permission) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addPermission {}",permission);
			permissionServiceImpl.insertPermission(permission);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addPermission异常 {}",permission,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_permission")
	public String getPermissionById(Long id,Model model) {
		log.info("getPermissionById  {}",id);
		Permission result = permissionServiceImpl.selectPermissionById(id);
		model.addAttribute("item",result);
		return "permission/updatePermission";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deletePermissionById(Long id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deletePermissionById  {}",id);
			permissionServiceImpl.deletePermissionById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deletePermissionById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdatePermission(Long id,Model model) throws Exception {
		Permission result = permissionServiceImpl.selectPermissionById(id);
		model.addAttribute("item",result);
		return "permission/updatePermission";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updatePermissionById(Permission permission) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updatePermissionById  {}",permission);
			permissionServiceImpl.updatePermissionById(permission);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updatePermissionById  {}",permission,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findPermissionList(Permission permission,Model model, HttpServletRequest request) {
		try {
			log.info("findPermissionList  {}",permission);
			PageUtil.doPage(request);
			Page<Permission> result = (Page<Permission>)permissionServiceImpl.findPermissionList(permission);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findPermissionList  {}",permission,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "permission/permissionList";
	}
}
