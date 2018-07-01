package com.cn.model;


public class RolePermission{
	/**
	 * 表id
	 **/
	private Long id;
	/**
	 * 角色id
	 **/
	private Long roleId;
	/**
	 * 权限id
	 **/
	private Long permissionId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("RolePermission{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (roleId != null) {
			sb.append(", \"roleId\":\"").append(roleId).append("\"");
		}
		if (permissionId != null) {
			sb.append(", \"permissionId\":\"").append(permissionId).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}