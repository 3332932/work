package com.cn.springboot.dao;

import com.cn.springboot.model.User;
import java.util.List;

public interface UserMapper {

	User selectUserById(Long user);

	int deleteUserById(Long user);

	int updateUserById(User user);

	int insertUser(User user);

	List<User> findUserList(User user);

}
