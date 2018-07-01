package com.cn.dao;

import com.cn.model.RolePermission;
import java.util.List;

public interface RolePermissionMapper {

	RolePermission selectRolePermissionById(Long rolePermission);

	int deleteRolePermissionById(Long rolePermission);

	int updateRolePermissionById(RolePermission rolePermission);

	int insertRolePermission(RolePermission rolePermission);

	List<RolePermission> findRolePermissionList(RolePermission rolePermission);

}
