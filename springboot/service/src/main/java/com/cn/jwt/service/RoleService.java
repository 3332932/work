package com.cn.jwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.jwt.entity.Role;
import com.cn.jwt.entity.User;

import java.util.List;

/**
 * @author yshh44
 */
public interface RoleService extends IService<Role> {


    List<Role> getRoleByUserId(Long userId);
}
