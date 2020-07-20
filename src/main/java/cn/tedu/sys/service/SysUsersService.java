package cn.tedu.sys.service;

import cn.tedu.common.vo.SysUserDeptVo;
import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysUsers;

import java.util.List;
import java.util.Map;

/**
 * 系统用户(SysUsers)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 16:38:25
 */
public interface SysUsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUsers queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUsers> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUsers 实例对象
     * @return 实例对象
     */
    SysUsers insert(SysUsers sysUsers);

    /**
     * 修改数据
     *
     * @param sysUsers 实例对象
     * @return 实例对象
     */
    SysUsers update(SysUsers sysUsers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    /*
     *分页查询操作
     * */
    PageObject<SysUserDeptVo> findPageObjects(String username,Integer pageCurrent);
    /*
    * 加修改用户装填
    * */
    int validById(Integer id,Integer valid);
    /*
    * 基于 id 查询用户及相关信息
    * */
    Map<String, Object> findObjectById(Integer userId);
    /*
    * 更新角色对象
    * */
    int updateObject(SysUsers entity,Integer[] roleIds);
    /*
    * 保存用户对象
    * */
    int saveObject(SysUsers entity,Integer[]roleIds);
    /*
    * 修改密码
    * */
    int updatePassword(String password,
                       String newPassword,
                       String cfgPassword);
}