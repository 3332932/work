package com.cn.jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.jwt.entity.Role;
import com.cn.jwt.mapper.RoleMapper;
import com.cn.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yshh44
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getRoleByUserId( Long userId){
        return roleMapper.getRoleByUserId(userId);
    }

}
