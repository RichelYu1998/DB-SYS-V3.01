package cn.tedu.sys.service.impl;

import cn.tedu.common.annotation.RequiredLog;
import cn.tedu.common.exception.ServiceException;
import cn.tedu.common.vo.SysUserDeptVo;
import cn.tedu.sys.dao.SysUsersDao;
import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysUsers;
import cn.tedu.sys.service.SysUsersService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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

    /*
     *分页查询操作
     * */
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
        if(id==null||id<1) {
            if(id==null||id<=0)
            throw new IllegalArgumentException("id值无效");
        }
        if(valid!=0&&valid!=1){
            throw new IllegalArgumentException(valid+" 状态值无效");
        }
        //2.更新用户状态
        int rows=sysUsersDao.validById(id, valid, "admin");//将来此用户为登陆
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }

}