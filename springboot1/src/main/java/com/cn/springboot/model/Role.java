package com.cn.springboot.model;


public class Role{
	/**
	 * 角色id
	 **/
	private Long id;
	/**
	 * 角色名
	 **/
	private String roleName;
	/**
	 * 角色标识,程序中判断使用,如"admin"
	 **/
	private String roleSign;
	/**
	 * 角色描述,UI界面显示使用
	 **/
	private String description;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleSign() {
		return roleSign;
	}

	public void setRoleSign(String roleSign) {
		this.roleSign = roleSign;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Role{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (roleName != null) {
			sb.append(", \"roleName\":\"").append(roleName).append("\"");
		}
		if (roleSign != null) {
			sb.append(", \"roleSign\":\"").append(roleSign).append("\"");
		}
		if (description != null) {
			sb.append(", \"description\":\"").append(description).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}