package cn.tedu.sys.service.impl;

import cn.tedu.common.annotation.RequiredLog;
import cn.tedu.common.exception.ServiceException;
import cn.tedu.common.vo.SysUserDeptVo;
import cn.tedu.sys.dao.SysUserRolesDao;
import cn.tedu.sys.dao.SysUsersDao;
import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysUsers;
import cn.tedu.sys.service.SysUsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;


import javax.annotation.Resource;
import javax.swing.text.TabableView;
import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 系统用户(SysUsers)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 16:38:25
 */
@Transactional(timeout = 30,
        readOnly = false,
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = Throwable.class,
        propagation = Propagation.REQUIRED)
@Service("sysUsersService")
public class SysUsersServiceImpl implements SysUsersService {
    @Resource
    private SysUsersDao sysUsersDao;
    @Resource
    private SysUserRolesDao sysUserRoleDao;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUsers queryById(Integer id) {
        return this.sysUsersDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUsers> queryAllByLimit(int offset, int limit) {
        return this.sysUsersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUsers 实例对象
     * @return 实例对象
     */
    @Override
    public SysUsers insert(SysUsers sysUsers) {
        this.sysUsersDao.insert(sysUsers);
        return sysUsers;
    }

    /**
     * 修改数据
     *
     * @param sysUsers 实例对象
     * @return 实例对象
     */
    @Override
    public SysUsers update(SysUsers sysUsers) {
        this.sysUsersDao.update(sysUsers);
        return this.queryById(sysUsers.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUsersDao.deleteById(id) > 0;
    }

    /*
     *分页查询操作
     * */
    @Transactional(readOnly = true)
    @RequiredLog("用户分页查询")
    @Override
    public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
        //1.对参数进行校验
        if (pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("当前页码值无效");
        //2.查询总记录数并进行校验
        int rowCount = sysUsersDao.getRowCount(username);
        if (rowCount == 0)
            throw new IllegalArgumentException("没有找到对应记录");
        //3.查询当前页记录
        int pageSize = 2;
        int startIndex = (pageCurrent - 1) * pageSize;
        List<SysUserDeptVo> records = sysUsersDao.findPageObjects(username, startIndex, pageSize);
        //4.对查询结果进行封装并返回
        return new PageObject<>(pageCurrent, pageSize, rowCount, records);
    }

    /*
     *禁用，启用业务
     * */
    @RequiredLog("禁用启用")
    @Override
    public int validById(Integer id, Integer valid) {
        //1.校验参数
        if (id == null || id < 1) {
            if (id == null || id <= 0)
                throw new IllegalArgumentException("id值无效");
        }
        if (valid != 0 && valid != 1) {
            throw new IllegalArgumentException(valid + " 状态值无效");
        }
        //2.更新用户状态
        int rows = sysUsersDao.validById(id, valid, "admin");//将来此用户为登陆
        if (rows == 0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }

    /*
     * 基于 id 查询用户及相关信息
     * */
    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> findObjectById(Integer id) {
        //1.参数校验
        if (id == null || id < 1)
            throw new IllegalArgumentException("参数不正确");
        //2.获取用户以及用户对应的部门信息
        SysUserDeptVo user = sysUsersDao.findObjectById(id);
        if (user == null)
            throw new ServiceException("用户可能已经不存在了");
        //3.获取用户对应的角色信息

        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(id);

        //4.封装查询结果并返回
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roleIds", roleIds);
        return map;

    }

    /*
     * 更新角色对象
     * */
    @Override
    public int updateObject(SysUsers entity, Integer[] roleIds) {
        //1.参数有效性验证
        if (entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getUsername()))
            throw new IllegalArgumentException("用户名不能为空");
        if (roleIds == null || roleIds.length == 0)
            throw new IllegalArgumentException("必须为其指定角色");
        //其它验证自己实现，例如用户名已经存在，密码长度，...
        //2.更新用户自身信息
        int rows = sysUsersDao.updateObject(entity);
        //3.保存用户与角色关系数据
        sysUserRoleDao.deleteObjectsByUserId(entity.getId());
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        return rows;
    }

    /*
     * 保存用户对象
     * */
    @Async
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Override
    public int saveObject(SysUsers entity, Integer[] roleIds) {
        //1.参数校验
        if (entity == null)
            throw new IllegalArgumentException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getUsername()))
            throw new IllegalArgumentException("用户名不能为空");
        if (StringUtils.isEmpty(entity.getPassword()))
            throw new IllegalArgumentException("密码不能为空");
        if (roleIds == null || roleIds.length == 0)
            throw new IllegalArgumentException("至少要为用户分配角色");
        //2.保存用户自身信息
        //2.1 对密码进行加密
        String source = entity.getPassword();
        String salt = UUID.randomUUID().toString();
        SimpleHash sh = new SimpleHash(//Shiro 框架
                "MD5",//algorithmName 算法
                source,//原密码
                salt,//颜值
                1);//hashIterations 表示加密次数
        entity.setSalt(salt);
        entity.setPassword(sh.toHex());
        int rows = sysUsersDao.insertObject(entity);
        //3.保存用户角色关系数据
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        //4.返回结果
        return rows;
    }

    /*
     * 修改密码
     * */
    @Override
    public int updatePassword(String password, String newPassword, String cfgPassword) {
        //1.判定新密码与密码确认是否相同
        if (StringUtils.isEmpty(newPassword))
            throw new IllegalArgumentException("新密码不能为空");
        if (StringUtils.isEmpty(cfgPassword))
            throw new IllegalArgumentException("确认密码不能为空");
        if (!newPassword.equals(cfgPassword))
            throw new IllegalArgumentException("两次输入的密码不相等");
        //2.判定原密码是否正确
        if (StringUtils.isEmpty(password))
            throw new IllegalArgumentException("原密码不能为空");
        //获取登陆用户
        SysUsers user = (SysUsers) SecurityUtils.getSubject().getPrincipal();
        SimpleHash sh = new SimpleHash("MD5", password, user.getSalt(), 1);
        if (!user.getPassword().equals(sh.toHex()))
            throw new IllegalArgumentException("原密码不正确");
        //3.对新密码进行加密
        String salt = UUID.randomUUID().toString();
        sh = new SimpleHash("MD5", newPassword, salt, 1);
        //4.将新密码加密以后的结果更新到数据
        int rows = sysUsersDao.updatePassword(sh.toHex(), salt, user.getId());
        if (rows == 0)
            throw new ServiceException("修改失败");
        return rows;
    }
}