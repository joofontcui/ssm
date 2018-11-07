package com.joofont.pojo;

import com.joofont.entity.Manage;
import com.joofont.service.ManageService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author cui jun on 2018/11/6.
 * @version 1.0
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ManageService manageService;

    /**
     * 提供用户信息返回权限信息
     * 注：AuthorizationInfo:角色的权限信息集合
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleName = manageService.findRoles(username);
        Set<String> permissions = manageService.findPermissions(username);
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 提供账户信息返回认证信息
     * 注：AuthenticationInfo：用户的角色信息集合
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户账号
        if(null == authenticationToken.getPrincipal()) return null;
        String name = authenticationToken.getPrincipal().toString();

        Manage manage = manageService.findUserByUsername(name);

        if (manage != null){
            //将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数随便放一个就行了。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(manage.getName(),manage.getPassword(),
                    "a");
            return authenticationInfo;
        }else{
//            return  null;
            throw new UnknownAccountException();
        }
    }

}
