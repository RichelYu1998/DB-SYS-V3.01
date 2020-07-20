package cn.tedu.sys.dao;

import cn.tedu.common.vo.Node;
import cn.tedu.sys.entity.SysDepts;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 部门管理(SysDepts)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:05
 */
@Mapper
public interface SysDeptsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDepts queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysDepts> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysDepts 实例对象
     * @return 对象列表
     */
    List<SysDepts> queryAll(SysDepts sysDepts);

    /**
     * 新增数据
     *
     * @param sysDepts 实例对象
     * @return 影响行数
     */
    int insert(SysDepts sysDepts);

    /**
     * 修改数据
     *
     * @param sysDepts 实例对象
     * @return 影响行数
     */
    int update(SysDepts sysDepts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
    /**
     * 查询所有部门以及部门的上级菜单信息
     * @return
     */
    @Select("select c.*,p.name parentName from sys_depts c left join sys_depts p on c.parentId=p.id")
    List<Map<String,Object>> findObjects();

    @Select("select id,name,parentId from sys_depts")
    List<Node> findZTreeNodes();

    int updateObject(SysDepts entity);
    int insertObject(SysDepts entity);

    @Select("select count(*) from sys_depts where parentId=#{id}")
    int getChildCount(Integer id);

    @Delete("delete from sys_depts where id=#{id}")
    int deleteObject(Integer id);
}