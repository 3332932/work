package com.cn.springboot.model;


public class User{
	/**
	 * 用户id
	 **/
	private Long id;
	/**
	 * 用户名
	 **/
	private String username;
	/**
	 * 密码
	 **/
	private String password;
	/**
	 * 状态
	 **/
	private String state;
	/**
	 * 创建时间
	 **/
	private String createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("User{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (username != null) {
			sb.append(", \"username\":\"").append(username).append("\"");
		}
		if (password != null) {
			sb.append(", \"password\":\"").append(password).append("\"");
		}
		if (state != null) {
			sb.append(", \"state\":\"").append(state).append("\"");
		}
		if (createTime != null) {
			sb.append(", \"createTime\":\"").append(createTime).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}