package com.cn.dao;

import com.cn.model.Role;
import java.util.List;

public interface RoleMapper {

	Role selectRoleById(Long role);

	int deleteRoleById(Long role);

	int updateRoleById(Role role);

	int insertRole(Role role);

	List<Role> findRoleList(Role role);

}
