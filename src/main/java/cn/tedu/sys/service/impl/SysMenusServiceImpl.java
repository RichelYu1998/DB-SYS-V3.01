package cn.tedu.sys.service.impl;

import cn.tedu.common.exception.ServiceException;
import cn.tedu.common.vo.Node;
import cn.tedu.sys.dao.SysMenusDao;
import cn.tedu.sys.dao.SysRoleMenusDao;
import cn.tedu.sys.entity.SysMenus;
import cn.tedu.sys.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资源管理(SysMenus)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 16:38:13
 */
@Service("sysMenusService")
public class SysMenusServiceImpl implements SysMenusService {
    @Resource
    private SysMenusDao sysMenusDao;
    @Resource
    private SysRoleMenusDao sysRoleMenuDao;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysMenus queryById(Integer id) {
        return this.sysMenusDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysMenus> queryAllByLimit(int offset, int limit) {
        return this.sysMenusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenus insert(SysMenus sysMenus) {
        this.sysMenusDao.insert(sysMenus);
        return sysMenus;
    }

    /**
     * 修改数据
     *
     * @param sysMenus 实例对象
     * @return 实例对象
     */
    @Override
    public SysMenus update(SysMenus sysMenus) {
        this.sysMenusDao.update(sysMenus);
        return this.queryById(sysMenus.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysMenusDao.deleteById(id) > 0;
    }

    /*
     * 菜单记录查询
     * */
    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> list = sysMenusDao.findObjects();
        if (list == null || list.size() == 0)
            throw new ServiceException("没有对应的菜单信息");
        return list;
    }

    /*
     * 基于 id 进行菜单删除
     * */
    @Override
    public int deleteObject(Integer id) {
        //1.验证数据的合法性
        if (id == null || id < 0)
            throw new IllegalArgumentException("请先选择");
        //2.基于 id 进行子元素查询
        int count = sysMenusDao.getChildCount(id);
        if (count > 0)
            throw new ServiceException("请先删除子菜单");
        //3.删除角色,菜单关系数据
        int rows = sysRoleMenuDao.deleteObjectsByMenuId(id);
        if (rows == 0)
            throw new ServiceException("此菜单可能已经不存在");
        //5.返回结果
        return rows;
    }

    /*
     * 查询菜单信息
     * */
    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenusDao.findZtreeMenuNodes();
    }

    /*
     * 保存菜单对象
     * */
    @Override
    public int saveObject(SysMenus entity) {
        //1.合法验证
        if (entity == null)
            throw new ServiceException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("菜单名不能为空");
        int rows;
        //2.保存数据
        try {
            rows = sysMenusDao.insertObject(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("保存失败");
        }
        //3.返回数据
        return rows;
    }

    /*
     *菜单保存
     * */
    @Override
    public int updateObject(SysMenus entity) {
        //1.合法验证
        if (entity == null)
            throw new ServiceException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("菜单名不能为空");
        //2.更新数据
        int rows = sysMenusDao.updateObject(entity);
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        //3.返回数据
        return rows;
    }
}