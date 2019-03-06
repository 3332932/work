package com.cn.jwt.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.jwt.entity.Permission;
import com.cn.jwt.entity.Role;
import com.cn.jwt.entity.User;
import com.cn.jwt.service.PermissionService;
import com.cn.jwt.service.impl.RoleServiceImpl;
import com.cn.jwt.service.impl.UserServiceImpl;
import com.cn.jwt.utils.ThreadLocals;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yshh44
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private RoleServiceImpl roleServiceImpl;
    @Autowired
    private PermissionService permissionServiceImpl;

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 提供用户信息，返回权限信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = ThreadLocals.getCurrentUser();
        List<Role> roleList = roleServiceImpl.getRoleByUserId(user.getUserId());

        Set<String> roleStr = new HashSet();
        List<Long> roleLong = new ArrayList<>();
        roleList.forEach(e -> {
            roleStr.add(e.getRoleName());
            roleLong.add(e.getRoleId());
        });
        user.setRole(roleStr);
        authorizationInfo.setRoles(roleStr);
        List<Permission> permissions = permissionServiceImpl.getPermissionByRoleIds(roleLong);
        Set<String> stringPermissions = new HashSet();
        permissions.forEach(e->stringPermissions.add(e.getPermissionValue()));
        if ("admin".equals(user.getUsername())){
            stringPermissions.add("user:list");
            stringPermissions.add("role:list");
            stringPermissions.add("permission:list");
            stringPermissions.add("role:grant");
            stringPermissions.add("permission:grant");

        }
        authorizationInfo.setStringPermissions(stringPermissions);

        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("---------------------------->登陆验证:");
        String userName = (String) authenticationToken.getPrincipal();
        User user = userServiceImpl.getOne(new QueryWrapper<User>().eq("user_name", userName));
        if (user == null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
        if (false) {
            //用户被锁定就抛异常
            throw new LockedAccountException();
        }
        //密码可以通过SimpleHash加密，然后保存进数据库。
        //此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
        //清空登录用户的权限（有时候上次登录的权限没有被清除）
        removeUserAuthorizationInfoCache(userName);
        ThreadLocals.setCurrentUser(user);
        return authenticationInfo;
    }


    public void removeUserAuthorizationInfoCache(String username) {
        SimplePrincipalCollection pc = new SimplePrincipalCollection();
        pc.add(username, super.getName());
        super.clearCachedAuthorizationInfo(pc);
    }

}
