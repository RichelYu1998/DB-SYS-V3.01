package cn.tedu.entity;

import java.io.Serializable;

/**
 * 角色与菜单对应关系(SysRoleMenus)实体类
 *
 * @author makejava
 * @since 2020-07-10 14:34:32
 */
public class SysRoleMenus implements Serializable {
    private static final long serialVersionUID = 210793703220398786L;

    private Integer id;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * ID
     */
    private Integer menuId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}