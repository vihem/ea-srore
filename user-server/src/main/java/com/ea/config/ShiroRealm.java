package com.ea.config;

import com.ea.entity.SysPermission;
import com.ea.entity.SysRole;
import com.ea.entity.User;
import com.ea.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 授权领域
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    /**
     * 获取授权信息
     * @param principalCollection 主要收集
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(" ## 权限配置 ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        System.out.println(" == user 用户信息 = " + user.show());
        for (SysRole role:user.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for (SysPermission permission:role.getPermissions()){
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录认证实现
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法。
     *      1、检查提交的进行认证的令牌信息
     *      2、根据令牌信息从数据源(通常为数据库)中获取用户信息
     *      3、对用户信息进行匹配验证。
     *      4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
     *      5、验证失败则抛出AuthenticationException异常信息。
     * @param token 身份验证令牌
     * @return AuthenticationInfo 身份验证信息 SimpleAuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println(" ## ShiroRealm.doGetAuthenticationInfo()");

        //获取用户账号
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (null == user){
            return null;
        }
        System.out.println(" ## User 用户信息 = " + user.show());
        //根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
        return new SimpleAuthenticationInfo(
                user, //principal,认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//盐值,salt=username+salt
                getName()  //realmName：当前realm对象的name，调用父类的getName()方法即可
        );
    }
}
