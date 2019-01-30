package com.cn.jwt.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cn.jwt.JwtConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 'ms.x'
 * @date 2017/7/21
 */
@TableName("user")
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{
	/**
	 * 用户id
	 */
	@JwtConfig
	@TableField("user_id")
	private Long userId;
	/**
	 * 用户名
	 */
	@JwtConfig
	@TableField("user_name")
	private String userName;
	/**
	 * 用户密码
	 */
	@TableField("password")
	private String password;
	/**
	 * 昵称
	 */
	@JwtConfig
	@TableField("nick_name")
	private String nickName;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	@TableField("update_time")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date updateTime;
	@TableField("salt")
	private String salt;

}
