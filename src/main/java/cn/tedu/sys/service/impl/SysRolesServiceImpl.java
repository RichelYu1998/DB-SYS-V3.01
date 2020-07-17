package cn.tedu.sys.service.impl;

import cn.tedu.common.exception.ServiceException;
import cn.tedu.sys.dao.SysRoleMenusDao;
import cn.tedu.sys.dao.SysRolesDao;
import cn.tedu.sys.dao.SysUserRolesDao;
import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysRoles;
import cn.tedu.sys.service.SysRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色(SysRoles)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 16:38:21
 */
@Service("sysRolesService")
public class SysRolesServiceImpl implements SysRolesService {
    @Resource
    private SysRolesDao sysRolesDao;
    @Resource
    private SysRoleMenusDao sysRoleMenuDao;
    @Resource
    private SysUserRolesDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRoles queryById(Long id) {
        return this.sysRolesDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoles> queryAllByLimit(int offset, int limit) {
        return this.sysRolesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRoles 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoles insert(SysRoles sysRoles) {
        this.sysRolesDao.insert(sysRoles);
        return sysRoles;
    }

    /**
     * 修改数据
     *
     * @param sysRoles 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoles update(SysRoles sysRoles) {
        this.sysRolesDao.update(sysRoles);
        return this.queryById(sysRoles.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysRolesDao.deleteById(id) > 0;
    }
    /*
     * 角色业务数据分页查询
     * */
    @Override
    public PageObject<SysRoles> findPageObjects(String name, Integer pageCurrent) {
        //1.对参数进行校验
        if(pageCurrent==null||pageCurrent<1)
            throw new IllegalArgumentException("当前页码值无效");
        //2.查询总记录数并进行校验
        int rowCount = sysRolesDao.getRowCount(name);
        if(rowCount==0)
            throw new ServiceException("没有找到对应记录");
        //3.查询当前页记录
        int pageSize=2;
        int startIndex=(pageSize-1)*pageSize;
        List<SysRoles> records = sysRolesDao.findPageObjects(name, startIndex, pageSize);
        return new PageObject<>(pageCurrent,pageSize,rowCount,records);
    }
    /*
     * 基于 id 进行角色删除
     * */
    @Override
    public int deleteObject(Integer id) {
        //1.验证数据的合法性
        if(id==null||id<1)
            throw  new IllegalArgumentException("请先选择");
        //3.基于 id 删除关系数据
        sysRoleMenuDao.deleteObjectsByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
        //4.删除角色自身
        int rows = sysRolesDao.deleteObject(id);
        if(rows==0)
            throw new ServiceException("此记录可能已经不存在");
        //5.返回结果
        return rows;
    }
}