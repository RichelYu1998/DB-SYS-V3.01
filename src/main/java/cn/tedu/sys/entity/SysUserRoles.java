package cn.tedu.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系(SysUserRoles)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:56:44
 */
@Data
public class SysUserRoles implements Serializable {
    private static final long serialVersionUID = -23610421560128420L;

    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 角色ID
     */
    private Integer roleId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}