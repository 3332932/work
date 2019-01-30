package com.cn.jwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.jwt.entity.Permission;
import com.cn.jwt.mapper.PermissionMapper;
import com.cn.jwt.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yshh44
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionByRoleId(Long roleId){
        return permissionMapper.getPermissionByRoleId(roleId);
    }
    @Override
    public List<Permission> getPermissionByRoleIds(List<Long> list){
        return permissionMapper.getPermissionByRoleIds(list);
    }

}
