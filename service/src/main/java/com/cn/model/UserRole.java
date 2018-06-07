package com.cn.model;


public class UserRole{
	/**
	 * 表id
	 **/
	private Long id;
	/**
	 * 用户id
	 **/
	private Long userId;
	/**
	 * 角色id
	 **/
	private Long roleId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("UserRole{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (userId != null) {
			sb.append(", \"userId\":\"").append(userId).append("\"");
		}
		if (roleId != null) {
			sb.append(", \"roleId\":\"").append(roleId).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}