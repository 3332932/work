package com.cn.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 'ms.x' on 2017/7/21.
 */
public class User implements Serializable{
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("User{");
		sb.append("userId=").append(userId);
		sb.append(", userName='").append(userName).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", nickName='").append(nickName).append('\'');
		sb.append(", createdTime=").append(createdTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append('}');
		return sb.toString();
	}
}
