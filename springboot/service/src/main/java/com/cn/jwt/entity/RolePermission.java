package com.cn.jwt.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cn.jwt.JwtConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
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
@TableName("role_permission")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolePermission implements Serializable{

	@TableField("role_id")
	private Long roleId;
	@TableField("permission_id")
	private Long permissionId;

}
