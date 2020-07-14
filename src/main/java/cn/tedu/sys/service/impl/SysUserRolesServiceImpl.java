package cn.tedu.sys.service.impl;

import cn.tedu.sys.dao.SysUserRolesDao;
import cn.tedu.sys.entity.SysUserRoles;
import cn.tedu.sys.service.SysUserRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户与角色对应关系(SysUserRoles)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 16:38:24
 */
@Service("sysUserRolesService")
public class SysUserRolesServiceImpl implements SysUserRolesService {
    @Resource
    private SysUserRolesDao sysUserRolesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserRoles queryById(Integer id) {
        return this.sysUserRolesDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUserRoles> queryAllByLimit(int offset, int limit) {
        return this.sysUserRolesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUserRoles 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRoles insert(SysUserRoles sysUserRoles) {
        this.sysUserRolesDao.insert(sysUserRoles);
        return sysUserRoles;
    }

    /**
     * 修改数据
     *
     * @param sysUserRoles 实例对象
     * @return 实例对象
     */
    @Override
    public SysUserRoles update(SysUserRoles sysUserRoles) {
        this.sysUserRolesDao.update(sysUserRoles);
        return this.queryById(sysUserRoles.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserRolesDao.deleteById(id) > 0;
    }
}