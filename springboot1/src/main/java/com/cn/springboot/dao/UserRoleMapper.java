package com.cn.springboot.dao;

import com.cn.springboot.model.UserRole;
import java.util.List;

public interface UserRoleMapper {

	UserRole selectUserRoleById(Long userRole);

	int deleteUserRoleById(Long userRole);

	int updateUserRoleById(UserRole userRole);

	int insertUserRole(UserRole userRole);

	List<UserRole> findUserRoleList(UserRole userRole);

}
