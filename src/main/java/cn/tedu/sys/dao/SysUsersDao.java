package cn.tedu.sys.dao;

import cn.tedu.common.vo.SysUserDeptVo;
import cn.tedu.sys.entity.SysUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * 系统用户(SysUsers)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:25
 */
@Mapper
public interface SysUsersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUsers queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUsers> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUsers 实例对象
     * @return 对象列表
     */
    List<SysUsers> queryAll(SysUsers sysUsers);

    /**
     * 新增数据
     *
     * @param sysUsers 实例对象
     * @return 影响行数
     */
    int insert(SysUsers sysUsers);

    /**
     * 修改数据
     *
     * @param sysUsers 实例对象
     * @return 影响行数
     */
    int update(SysUsers sysUsers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
    /*
     * 查询用户记录总数以及当前页面
     * */
    int getRowCount( String username);
    List<SysUserDeptVo> findPageObjects(
            String username,
            Integer startIndex,
            Integer pageSize
    );
    /*
    * 修改用户状态信息
    * */
    int validById(
            @Param("id")Integer id,
            @Param("valid")Integer valid,
            @Param("modifiedUser")String modifiedUser);
    /*
    * 基于用户 id 查询用户相关信息
    * */
    SysUserDeptVo findObjectById(Integer id);
    /*
    * 数据更新
    * */
    int updateObject(SysUsers entity);
    /*
    * 用户提交
    * */
    int insertObject(SysUsers entity);
    /*
    * 修改用户密码信息
    * */
    int updatePassword(
            @Param("password")String password,
            @Param("salt")String salt,
            @Param("id")Integer id
    );
    /*
    * 根据用户名获取用户对象
    * */
    SysUsers findUserByUserName(String username);
}