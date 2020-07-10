package cn.tedu.service.impl;

import cn.tedu.entity.SysRoleMenus;
import cn.tedu.dao.SysRoleMenusDao;
import cn.tedu.service.SysRoleMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色与菜单对应关系(SysRoleMenus)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 11:52:06
 */
@Service("sysRoleMenusService")
public class SysRoleMenusServiceImpl implements SysRoleMenusService {
    @Resource
    private SysRoleMenusDao sysRoleMenusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRoleMenus queryById(Integer id) {
        return this.sysRoleMenusDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoleMenus> queryAllByLimit(int offset, int limit) {
        return this.sysRoleMenusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRoleMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleMenus insert(SysRoleMenus sysRoleMenus) {
        this.sysRoleMenusDao.insert(sysRoleMenus);
        return sysRoleMenus;
    }

    /**
     * 修改数据
     *
     * @param sysRoleMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleMenus update(SysRoleMenus sysRoleMenus) {
        this.sysRoleMenusDao.update(sysRoleMenus);
        return this.queryById(sysRoleMenus.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysRoleMenusDao.deleteById(id) > 0;
    }
}