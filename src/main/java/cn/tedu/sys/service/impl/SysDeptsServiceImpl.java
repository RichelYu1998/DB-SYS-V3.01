package cn.tedu.sys.service.impl;

import cn.tedu.common.exception.ServiceException;
import cn.tedu.common.vo.Node;
import cn.tedu.sys.dao.SysDeptsDao;
import cn.tedu.sys.entity.SysDepts;
import cn.tedu.sys.service.SysDeptsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 部门管理(SysDepts)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 16:38:08
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
     * @param limit  查询条数
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
    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> list=
                sysDeptsDao.findObjects();
        if(list==null||list.size()==0)
            throw new ServiceException("没有部门信息");
        return list;
    }
    @Override
    public List<Node> findZTreeNodes() {
        List<Node> list=
                sysDeptsDao.findZTreeNodes();
        if(list==null||list.size()==0)
            throw new ServiceException("没有部门信息");
        return list;
    }
    @Override
    public int updateObject(SysDepts entity) {
        //1.合法验证
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("部门不能为空");
        int rows;
        //2.更新数据
        try{
            rows=sysDeptsDao.updateObject(entity);
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException("更新失败");
        }
        //3.返回数据
        return rows;
    }
    @Override
    public int saveObject(SysDepts entity) {
        //1.合法验证
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("部门不能为空");
        //2.保存数据
        int rows=sysDeptsDao.insertObject(entity);
        //if(rows==1)
        //throw new ServiceException("save error");
        //3.返回数据
        return rows;
    }
    @Override
    public int deleteObject(Integer id) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new ServiceException("数据不合法,id="+id);
        //2.执行删除操作
        //2.1判定此id对应的菜单是否有子元素
        int childCount=sysDeptsDao.getChildCount(id);
        if(childCount>0)
            throw new ServiceException("此元素有子元素，不允许删除");
        //2.2判定此部门是否有用户
        //int userCount=sysUserDao.getUserCountByDeptId(id);
        //if(userCount>0)
        //throw new ServiceException("此部门有员工，不允许对部门进行删除");
        //2.2判定此部门是否已经被用户使用,假如有则拒绝删除
        //2.3执行删除操作
        int rows=sysDeptsDao.deleteObject(id);
        if(rows==0)
            throw new ServiceException("此信息可能已经不存在");
        return rows;
    }

}