package com.cn.jwt.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.jwt.entity.User;
import com.cn.jwt.service.UserService;
import com.cn.jwt.utils.ThreadLocals;
import com.cn.jwt.utils.WorkUtils;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yshh44
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userServiceImpl;


    @GetMapping("/list")
    @RequiresPermissions("user:list")
    public Object getInfo(HttpServletRequest request){
        User currentUser = ThreadLocals.getCurrentUser();
        QueryWrapper wrapper = new QueryWrapper();
        WorkUtils.sort(wrapper,request);
        WorkUtils.startPage(request);
        Page<User> page = (Page) userServiceImpl.list(wrapper);
        return  WorkUtils.getResultPageDataMap(page.getResult(),page.getTotal());
    }

    @GetMapping("/dtl")
    public Object getDtl(HttpServletRequest request){
        User currentUser = ThreadLocals.getCurrentUser();
        Wrapper wrapper = new QueryWrapper();
        List<User> list = userServiceImpl.list(wrapper);
        return  WorkUtils.getResultDataMap(list);
    }

}

