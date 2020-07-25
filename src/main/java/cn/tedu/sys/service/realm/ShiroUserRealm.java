package cn.tedu.sys.service.realm;

import cn.tedu.sys.dao.SysMenusDao;
import cn.tedu.sys.dao.SysRoleMenusDao;
import cn.tedu.sys.dao.SysUserRolesDao;
import cn.tedu.sys.dao.SysUsersDao;
import cn.tedu.sys.entity.SysUsers;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
    @Resource
    private SysUsersDao sysUsersDao;
    @Resource
    private SysUserRolesDao sysUserRolesDao;
    @Resource
    private SysRoleMenusDao sysRoleMenusDao;
    @Resource
    private SysMenusDao sysMenusDao;
    /**
     * 设置凭证匹配器(与用户添加操作使用相同的加密算法)
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){
        //构建凭证匹配对象
        HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
        //设置加密算法
        cMatcher.setHashAlgorithmName("MD5");
        //设置加密次数
        cMatcher.setHashIterations(1);
        super.setCredentialsMatcher(cMatcher);
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.获取登录用户信息，例如用户 id
        SysUsers user = (SysUsers) principals.getPrimaryPrincipal();
        Integer userId = user.getId();
        //2.基于用户 id 获取用户拥有的角色(sys_user_roles)
        List<Integer> roleIds = sysUserRolesDao.findRoleIdsByUserId(userId);
        if(roleIds==null||roleIds.size()==0)
            throw new AuthorizationException();
        //3.基于角色 id 获取菜单 id(sys_role_menus)
        Integer[] array={};
        List<Integer> menuIds = sysRoleMenusDao.findMenuIdsByRoleIds(roleIds.toArray(array));
        if(menuIds==null||menuIds.size()==0)
            throw new AuthorizationException();
        //4.基于菜单 id 获取权限标识(sys_menus)
        List<String> permissions = sysMenusDao.findPermissions(menuIds.toArray(array));
        //5.对权限标识信息进行封装并返回
        Set<String> set = new HashSet<>();
        for (String per:permissions) {
            if(StringUtils.isEmpty(per)){
                set.add(per);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }
    /**
     * 通过此方法完成认证数据的获取及封装,系统
     * 底层会将认证数据传递认证管理器，由认证
     * 管理器完成认证操作。
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户名(用户页面输入)
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username=upToken.getUsername();
        //2.基于用户名查询用户信息
        SysUsers user = sysUsersDao.findUserByUserName(username);
        //3.判定用户是否存在
        if(user==null)
            throw new UnknownAccountException();
        //4.判定用户是否已被禁用。
        if(user.getValid()==0)
            throw new LockedAccountException();
        //5.封装用户信息
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,//principal (身份)
                user.getPassword(),//hashedCredentials
                credentialsSalt, //credentialsSalt
                getName());//realName
        //6.返回封装结果
        return info;//返回值会传递给认证管理器
        // (后续认证管理器会通过此信息完成认证操作)
    }
}
