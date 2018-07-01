package com.cn.dao;

import com.cn.model.Permission;
import java.util.List;

public interface PermissionMapper {

	Permission selectPermissionById(Long permission);

	int deletePermissionById(Long permission);

	int updatePermissionById(Permission permission);

	int insertPermission(Permission permission);

	List<Permission> findPermissionList(Permission permission);

}
