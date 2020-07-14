package cn.tedu.sys.service.impl;

import cn.tedu.sys.dao.SysUsersDao;
import cn.tedu.sys.entity.SysUsers;
import cn.tedu.sys.service.SysUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户(SysUsers)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 16:38:25
 */
@Service("sysUsersService")
public class SysUsersServiceImpl implements SysUsersService {
    @Resource
    private SysUsersDao sysUsersDao;

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
}