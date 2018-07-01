package com.cn.dao;


import com.cn.model.User;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    
    User selectByUserName(String userName);
    
    Page getUserPage(User user);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}