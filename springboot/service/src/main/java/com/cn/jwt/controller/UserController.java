package com.cn.jwt.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.override.PageMapperMethod;
import com.cn.jwt.entity.Role;
import com.cn.jwt.entity.User;
import com.cn.jwt.service.RoleService;
import com.cn.jwt.service.UserService;
import com.cn.jwt.utils.ThreadLocals;
import com.cn.jwt.utils.WorkUtils;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yshh44
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RoleService roleServiceImpl;
    @GetMapping("/list")
    @RequiresPermissions("user:list")
    public Object getInfo(User user,HttpServletRequest request){
        User currentUser = ThreadLocals.getCurrentUser();
        WorkUtils.setBlankToNull(user);
        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(user.getUsername())){
            wrapper.likeRight("user_name",user.getUsername());
            user.setUsername(null);
        }
        wrapper.setEntity(user);
        WorkUtils.sort(wrapper,request);
        WorkUtils.startPage(request);
        Page<User> page = (Page) userServiceImpl.list(wrapper);
        return  WorkUtils.getResultPageDataMap(page.getResult(),page.getTotal());
    }

    @GetMapping("/get")
    public Object getDtl(HttpServletRequest request){
        User currentUser = ThreadLocals.getCurrentUser();
        Wrapper wrapper = new QueryWrapper();
        ((QueryWrapper) wrapper).eq("user_name",currentUser.getUsername());
        User user = userServiceImpl.getOne(wrapper);
        List<Role> roles = roleServiceImpl.getRoleByUserId(user.getUserId());
        List<String> roleStr = new ArrayList<>();
        roles.forEach(e->{
            roleStr.add(e.getRoleName());
        });
        Map<String, Object> resultDataMap = WorkUtils.getResultDataMap(user);
        resultDataMap.put("roles",roleStr);
        return  resultDataMap;
    }

}

