package com.cn.model;


public class Permission{
	/**
	 * 权限id
	 **/
	private Long id;
	/**
	 * 权限名
	 **/
	private String permissionName;
	/**
	 * 权限标识,程序中判断使用,如"user:create"
	 **/
	private String permissionSign;
	/**
	 * 权限描述,UI界面显示使用
	 **/
	private String description;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionSign() {
		return permissionSign;
	}

	public void setPermissionSign(String permissionSign) {
		this.permissionSign = permissionSign;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Permission{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (permissionName != null) {
			sb.append(", \"permissionName\":\"").append(permissionName).append("\"");
		}
		if (permissionSign != null) {
			sb.append(", \"permissionSign\":\"").append(permissionSign).append("\"");
		}
		if (description != null) {
			sb.append(", \"description\":\"").append(description).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}