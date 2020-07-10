package cn.tedu.service.impl;

import cn.tedu.entity.SysDepts;
import cn.tedu.dao.SysDeptsDao;
import cn.tedu.service.SysDeptsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门管理(SysDepts)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 11:52:05
 */
@Service("sysDeptsService")
public class SysDeptsServiceImpl implements SysDeptsService {
    @Resource
    private SysDeptsDao sysDeptsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysDepts queryById(Integer id) {
        return this.sysDeptsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysDepts> queryAllByLimit(int offset, int limit) {
        return this.sysDeptsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysDepts 实例对象
     * @return 实例对象
     */
    @Override
    public SysDepts insert(SysDepts sysDepts) {
        this.sysDeptsDao.insert(sysDepts);
        return sysDepts;
    }

    /**
     * 修改数据
     *
     * @param sysDepts 实例对象
     * @return 实例对象
     */
    @Override
    public SysDepts update(SysDepts sysDepts) {
        this.sysDeptsDao.update(sysDepts);
        return this.queryById(sysDepts.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysDeptsDao.deleteById(id) > 0;
    }
}